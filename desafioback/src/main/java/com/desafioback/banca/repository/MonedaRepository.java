package com.desafioback.banca.repository;

import com.desafioback.banca.entities.Moneda;
import com.desafioback.banca.entities.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author GAMER HP
 */
public interface MonedaRepository extends JpaRepository<Moneda, Long> {
    
    @Query("SELECT m FROM Moneda m WHERE m.id =?1 ")
    public Moneda getById(long id );
    
    @Query("SELECT m FROM Moneda m WHERE m.Nombre =?1 ")
    public Moneda getByName(String Nombre);
}
