package com.desafioback.banca.repository;

import com.desafioback.banca.entities.Transaccion;
import com.desafioback.banca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author GAMER HP
 */
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
   
}
