package com.desafioback.banca.repository;


import com.desafioback.banca.entities.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author GAMER HP
 */
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
    
    @Query("SELECT t FROM TipoCambio t WHERE t.id =?1 ")
    public TipoCambio getById(long id );
}
