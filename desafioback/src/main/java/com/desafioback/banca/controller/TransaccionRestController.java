package com.desafioback.banca.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafioback.banca.DaoImp.TransaccionImp;
import com.desafioback.banca.entities.Moneda;
import com.desafioback.banca.entities.Response;
import com.desafioback.banca.entities.TipoCambio;
import com.desafioback.banca.entities.Transaccion;
import com.desafioback.banca.entities.TransaccionDtoImp;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<Response> list() {
        Response response = new Response();
        try {
            List<Transaccion> save = transaccionRepository.findAll();
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
            Transaccion save = transaccionRepository.getById(id);
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

//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Transaccion input) {
//        return null;
//    }
    @PostMapping
    public ResponseEntity<Response> post(@RequestBody TransaccionDtoImp input) {
        Response response = new Response();
        try {
            Moneda monedaorigen = monedaRepository.getById(input.getIdmoneda_origen());
            Moneda monedadestino = monedaRepository.getById(input.getIdmoneda_destino());
            TipoCambio tipo = tipoCambioRepository.getById(input.getIdtipocambio());
            Usuario usuario = usuarioRepository.getById(input.getIdusuario());
            double MontoCambio = 0;
            if (monedaorigen != null && monedadestino != null && tipo != null && usuario != null) {
                MontoCambio = transaccionImp.Calculo(monedaorigen, monedadestino, tipo, usuario, input);
                if (MontoCambio != 0) {
                    input.setMontoCambio(MontoCambio);
                    Transaccion transaccion = new Transaccion();
                    transaccion.setMoneda_origen(new Moneda(input.getIdmoneda_origen()));
                    transaccion.setMoneda_destino(new Moneda(input.getIdmoneda_destino()));
                    transaccion.setTipocambio(new TipoCambio(input.getIdtipocambio()));
                    transaccion.setUsuario(new Usuario(input.getIdusuario()));
                    transaccion.setFecha(input.getFecha());
                    transaccion.setMonto(input.getMonto());
                    transaccion.setMontoCambio(MontoCambio);
                    Transaccion save = transaccionRepository.save(transaccion);
                    save.setMoneda_origen(monedaorigen);
                    save.setMoneda_destino(monedadestino);
                    save.setTipocambio(tipo);
                    save.setUsuario(usuario);
                    response.setCodestado(200);
                    response.setEstado("ok");
                    response.setMensaje("");
                    response.setToken("");
                    response.setData(save);
                    return ResponseEntity.ok(response);
                } else {
                    response.setCodestado(400);
                    response.setEstado("bad Request");
                    response.setMensaje("verificar data, Error al ejecutar operacion");
                    response.setToken("");
                    response.setData(null);
                    return ResponseEntity.badRequest().body(response);
                }

            } else {
                response.setCodestado(400);
                response.setEstado("bad Request");
                response.setMensaje("Id Incorrecto, verificar data");
                response.setToken("");
                response.setData(null);
                return ResponseEntity.badRequest().body(response);
            }

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
