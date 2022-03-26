package com.desafioback.banca.repository;

import com.desafioback.banca.entities.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author GAMER HP
 */
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    
    @Query("SELECT t FROM Transaccion t WHERE t.id =?1 ")
    public Transaccion getById(long id );
   
}
