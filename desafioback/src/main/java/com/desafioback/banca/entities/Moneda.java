package com.desafioback.banca.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
// @author GAMER HP

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Moneda {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String Nombre;
    private Double ValorCompra;
    private Double ValorReal;
    private Double ValorVenta;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,targetEntity = Transaccion.class)    
    @JoinColumn(name = "moneda_id", nullable = true)
    private Transaccion transaccion;
}
