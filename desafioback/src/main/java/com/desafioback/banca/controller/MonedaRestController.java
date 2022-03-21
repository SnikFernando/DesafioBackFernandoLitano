package com.desafioback.banca.controller;

import com.desafioback.banca.entities.Moneda;
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
    public List<Moneda> list() {
        return monedaRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Moneda get(@PathVariable long id) {
        return monedaRepository.getById(id);
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Moneda input) {
//        return null;
//    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Moneda input) {
        Moneda save = monedaRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
    
}
