package com.desafioback.banca.entities;

// @author GAMER HP

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String dni;
    private String rol;
    private String correo;
    private String contrasena;
    
    @JsonIgnore
    
    @OneToOne(fetch = FetchType.LAZY,targetEntity = Transaccion.class)    
    @JoinColumn(name = "usuario_id", nullable = true)
    private Transaccion transaccion;
}
