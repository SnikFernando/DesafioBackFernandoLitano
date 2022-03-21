package com.desafioback.banca.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
// @author GAMER HP

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoCambio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String Nombre;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,targetEntity = Transaccion.class)    
    @JoinColumn(name = "tipo_id", nullable = true)
    private Transaccion transaccion;
}
