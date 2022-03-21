package com.desafioback.banca.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafioback.banca.DaoImp.TransaccionImp;
import com.desafioback.banca.entities.Moneda;
import com.desafioback.banca.entities.TipoCambio;
import com.desafioback.banca.entities.Transaccion;
import com.desafioback.banca.entities.Usuario;
import com.desafioback.banca.repository.MonedaRepository;
import com.desafioback.banca.repository.TipoCambioRepository;
import com.desafioback.banca.repository.TransaccionRepository;
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
@RequestMapping("/transaccion")
public class TransaccionRestController {

    @Autowired
    TransaccionRepository transaccionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MonedaRepository monedaRepository;
    @Autowired
    TipoCambioRepository tipoCambioRepository;
    
    
    TransaccionImp transaccionImp = new TransaccionImp();

    @GetMapping()
    public List<Transaccion> list() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmZXJuYW5kbyIsImlkIjoyLCJkbmkiOiI3NjY1Mjc4NCJ9.g93gf4gPxGiQZNzHGZMVM6KYstuuOi9EFIsQI0bvYcs";
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
//                    .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
//            long id = jwt.getClaim("id").asLong();
//            Usuario usuario = usuarioRepository.getById(id );
//            if(usuario!=null){
                return transaccionRepository.findAll();
//            }
//            return null;
            
//        } catch (JWTVerificationException exception) {
//            return null;
//        }
    }

    @GetMapping("/{id}")
    public Transaccion get(@PathVariable long id) {
        return transaccionRepository.getById(id);
        
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Transaccion input) {
//        return null;
//    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transaccion input) {
        
        Moneda monedaorigen = monedaRepository.getById(input.getMoneda_origen().getId());
        Moneda monedadestino = monedaRepository.getById(input.getMoneda_destino().getId());
        TipoCambio tipo = tipoCambioRepository.getById(input.getTipocambio().getId());
        Usuario usuario = usuarioRepository.getById(input.getUsuario().getId());
        double monto = transaccionImp.Calculo(monedaorigen,monedadestino,tipo,usuario,input);        
        if (monto != 0) {
            input.setMontoCambio(monto);
            Transaccion save = transaccionRepository.save(input);
            return ResponseEntity.ok(transaccionRepository.getById(save.getId()));
        }
        return ResponseEntity.badRequest().body("El tipo de transaccion no esta disponible");
        
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }

}
