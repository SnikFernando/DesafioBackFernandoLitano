package com.desafioback.banca.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.desafioback.banca.DaoImp.TransaccionImp;
import com.desafioback.banca.entities.Response;
import com.desafioback.banca.entities.Usuario;
import com.desafioback.banca.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    TransaccionImp transaccionImp = new TransaccionImp();

    @GetMapping()
    public ResponseEntity<Response> list() {
        Response response = new Response();
        try {
            List<Usuario> save = usuarioRepository.findAll();
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
            Usuario save = usuarioRepository.getById(id);
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
            response.setMensaje("El usurio no existe");
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
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Usuario input) {
//        return null;
//    }
    @PostMapping
    public ResponseEntity<Response> post(@RequestBody Usuario input) {
        Response response = new Response();
        try {
            Usuario existeUsuario = usuarioRepository.getCorreo(input.getCorreo());
            if(existeUsuario!=null){
                response.setCodestado(400);
                response.setEstado("Bad Request");
                response.setMensaje("El Usuario a crear ya existe");
                response.setToken("");
                response.setData(null);
                return ResponseEntity.badRequest().body(response);
            }
            Usuario save = usuarioRepository.save(input);
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

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Usuario input) {
        Response response = new Response();
        try {
            Usuario save = usuarioRepository.Login(input.getCorreo(), input.getContrasena());
            String token = "";
            if (save != null) {               
                token = transaccionImp.Encode(save);
                if(token.equals("")){
                response.setCodestado(400);
                response.setEstado("Bad Request");
                response.setMensaje("Error al generar el token");
                response.setToken("");
                response.setData(null);  
                    
                }
                response.setCodestado(200);
                response.setEstado("ok");
                response.setMensaje("");
                response.setToken(token);
                response.setData(save);
                return ResponseEntity.ok(response);
            }
            response.setCodestado(400);
            response.setEstado("Bad Request");
            response.setMensaje("El usurio no es valido");
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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
}
