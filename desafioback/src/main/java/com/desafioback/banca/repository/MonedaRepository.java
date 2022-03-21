/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.desafioback.banca.repository;

import com.desafioback.banca.entities.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author GAMER HP
 */
public interface MonedaRepository extends JpaRepository<Moneda, Long> {
    
}
