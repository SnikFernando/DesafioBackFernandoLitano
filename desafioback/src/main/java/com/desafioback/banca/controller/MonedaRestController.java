package com.desafioback.banca.controller;

import com.desafioback.banca.entities.Moneda;
import com.desafioback.banca.entities.Response;
import com.desafioback.banca.repository.MonedaRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author GAMER HP
 */
@RestController
@RequestMapping("/moneda")
public class MonedaRestController {

    @Autowired
    MonedaRepository monedaRepository;

    @GetMapping()
    public ResponseEntity<Response> list() {
        Response response = new Response();
        try {
            List<Moneda> save = monedaRepository.findAll();
            response.setCodestado(200);
            response.setEstado("ok");
            response.setMensaje("");
            response.setToken("");
            response.setData(save);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCodestado(500);
            response.setEstado("Error Interno del Servidor");
            response.setMensaje(e.getMessage());
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable long id) {
        Response response = new Response();
        try {
            Moneda save = monedaRepository.getById(id);
            if (save != null) {
                response.setCodestado(200);
                response.setEstado("ok");
                response.setMensaje("");
                response.setToken("");
                response.setData(save);
                return ResponseEntity.ok(response);
            }
            response.setCodestado(200);
            response.setEstado("ok");
            response.setMensaje("La Entidad no existe");
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setCodestado(500);
            response.setEstado("Error Interno del Servidor");
            response.setMensaje(e.getMessage());
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody Moneda input) {

        Response response = new Response();
        try {
            Moneda existe = monedaRepository.getByName(input.getNombre());
            if(existe==null){
                if(input.isIsnacional()){
                    if (input.getValorCompra() != 1 || input.getValorReal() != 1 || input.getValorVenta() != 1) {
                        response.setCodestado(400);
                        response.setEstado("Bad Request");
                        response.setMensaje("La moneda nacional deben tener valor 1 en VC, VR y VV");
                        response.setToken("");
                        response.setData(null);
                        return ResponseEntity.badRequest().body(response);
                    }
                }
                Moneda save = monedaRepository.save(input);
                response.setCodestado(200);
                response.setEstado("ok");
                response.setMensaje("");
                response.setToken("");
                response.setData(save);
                return ResponseEntity.ok(response); 
            }
            response.setCodestado(400);
            response.setEstado("Bad Request");
            response.setMensaje("La Moneda ya existe");
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
            
        } catch (Exception e) {
            response.setCodestado(500);
            response.setEstado("Error Interno del Servidor");
            response.setMensaje(e.getMessage());
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Moneda input) {
//        return null;
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
}
