package com.desafioback.banca.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafioback.banca.entities.TipoCambio;
import com.desafioback.banca.entities.Usuario;
import com.desafioback.banca.repository.TipoCambioRepository;
import com.desafioback.banca.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
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
    
    @GetMapping()
    public List<TipoCambio> list(@RequestHeader(value = "token")  String token) {
        
       
        try {
            Algorithm algorithm = Algorithm.HMAC256("PAASSSSWORR123");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
           
            DecodedJWT jwt = verifier.verify(token);
            long id = jwt.getClaim("id").asLong();
            Usuario usuario = usuarioRepository.getById(id );
            if(usuario!=null){
                return  tipoCambioRepository.findAll();
            }
            return null;
            
        } catch (JWTVerificationException exception) {
            return null;
        }
        
    }
    
    @GetMapping("/{id}")
    public TipoCambio get(@PathVariable long id) {
        return tipoCambioRepository.getById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody TipoCambio input) {
        try {
            TipoCambio tipo = tipoCambioRepository.getById(id);
            if(tipo!=null){
                TipoCambio save = tipoCambioRepository.save(input);
                return ResponseEntity.ok(save);
            }
            return ResponseEntity.badRequest().body("La Entidad a Actualizar no existe");
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("La Entidad a Actualizar no existe");
        }
        

    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TipoCambio input) {
    	TipoCambio save = tipoCambioRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        
        tipoCambioRepository.deleteById(id);
        return ResponseEntity.ok("se Elinmino correctamente");
    }
    
}
