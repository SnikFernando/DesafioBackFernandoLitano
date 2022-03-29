package com.desafioback.banca.controller;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafioback.banca.DaoImp.TransaccionImp;
import com.desafioback.banca.entities.Response;
import com.desafioback.banca.entities.TipoCambio;
import com.desafioback.banca.entities.Usuario;
import com.desafioback.banca.repository.TipoCambioRepository;
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
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author GAMER HP
 */
@RestController
@RequestMapping("/tipocambio")
public class TipoCambioRestController {

    @Autowired
    TipoCambioRepository tipoCambioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    TransaccionImp transaccionImp = new TransaccionImp();
    
    @GetMapping()
    public ResponseEntity<Response> list(@RequestHeader("Authorization") String token) {
        Response response = new Response();
        try {           
            long id = transaccionImp.Decode(token);
            Usuario usuario = usuarioRepository.getById(id);
            if (usuario != null) {
                List<TipoCambio> save = tipoCambioRepository.findAll();
                response.setCodestado(200);
                response.setEstado("ok");
                response.setMensaje("");
                response.setToken("");
                response.setData(save);
                return ResponseEntity.ok(response);
            } else {
                response.setCodestado(400);
                response.setEstado("Bad Request");
                response.setMensaje("Data incorrecta");
                response.setToken("");
                response.setData(null);
                return ResponseEntity.badRequest().body(response);
            }

        }catch(JWTVerificationException exception){
            response.setCodestado(400);
            response.setEstado("Bad Request");
            response.setMensaje("Token Expirado o No valido");
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
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
            TipoCambio save = tipoCambioRepository.getById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<Response> put(@RequestHeader("Authorization") String token, @PathVariable long id, @RequestBody TipoCambio input) {
        Response response = new Response();
        try { 
            long idUsuario = transaccionImp.Decode(token);
            Usuario usuario = usuarioRepository.getById(idUsuario);
            if (usuario != null) {
                
                TipoCambio tipo = tipoCambioRepository.getById(id);
                if (tipo != null) {
                    input.setId(id);                    
                    TipoCambio save = tipoCambioRepository.save(input);
                    response.setCodestado(200);
                    response.setEstado("ok");
                    response.setMensaje("");
                    response.setToken("");
                    response.setData(save);
                    return ResponseEntity.ok(response);
                } else {
                    response.setCodestado(200);
                    response.setEstado("ok");
                    response.setMensaje("La Entidad a Actualizar no existe");
                    response.setToken("");
                    response.setData(null);
                    return ResponseEntity.badRequest().body(response);
                }
            }
            else {
                response.setCodestado(400);
                response.setEstado("Bad Request");
                response.setMensaje("Data incorrecta");
                response.setToken("");
                response.setData(null);
                return ResponseEntity.badRequest().body(response);
            }           
            

        } catch(JWTVerificationException exception){
            response.setCodestado(400);
            response.setEstado("Bad Request");
            response.setMensaje("Token No valido");
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e) {
            response.setCodestado(500);
            response.setEstado("Error Interno del Servidor");
            response.setMensaje(e.getMessage());
            response.setToken("");
            response.setData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody TipoCambio input) {

        Response response = new Response();
        try {
            TipoCambio existe = tipoCambioRepository.getByName(input.getNombre());
            if(existe==null){
                TipoCambio save = tipoCambioRepository.save(input);
                response.setCodestado(200);
                response.setEstado("ok");
                response.setMensaje("");
                response.setToken("");
                response.setData(save);
                return ResponseEntity.ok(response);  
            }
            
            response.setCodestado(400);
            response.setEstado("Bad Request");
            response.setMensaje("Tipo de cambio ya existe");
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable long id) {
        Response response = new Response();
        try {
            tipoCambioRepository.deleteById(id);
            response.setCodestado(200);
            response.setEstado("ok");
            response.setMensaje("Se elimino Correctamente");
            response.setToken("");
            response.setData(null);
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

}
