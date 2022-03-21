package com.desafioback.banca.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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
    
    @GetMapping()
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuario get(@PathVariable long id) {
        return usuarioRepository.getById(id);
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Usuario input) {
//        return null;
//    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Usuario input) {
        Usuario save = usuarioRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario input) {
        Usuario save = usuarioRepository.Login(input.getCorreo(),input.getContrasena());
         String token ="";
        if(save.getId()!=0){
            try {
                Algorithm algorithm = Algorithm.HMAC256("PAASSSSWORR123");
                token = JWT.create()
                        .withClaim("id", save.getId())
                        .withClaim("dni", save.getDni())
                        .withIssuer("auth0")
                        .sign(algorithm);
            } catch (JWTCreationException exception) {
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
        }
        return ResponseEntity.ok(token);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
    
}
