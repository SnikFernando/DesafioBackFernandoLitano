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
import javax.persistence.Table;
import javax.persistence.Transient;
// @author GAMER HP

@Entity
@Table()
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Moneda {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Nombre;
    private Double ValorCompra;
    private Double ValorReal;
    private Double ValorVenta;
    private boolean isnacional;
    
    @JsonIgnore
    @Transient
    @OneToOne(fetch = FetchType.LAZY,targetEntity = Transaccion.class)    
    @JoinColumn(name = "transaccion_id", nullable = true)
    private Transaccion transaccion;

    public Moneda() {
    }

    public Moneda(long Id) {
        this.Id = Id;
       
    }
    
    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Double getValorCompra() {
        return ValorCompra;
    }

    public void setValorCompra(Double ValorCompra) {
        this.ValorCompra = ValorCompra;
    }

    public Double getValorReal() {
        return ValorReal;
    }

    public void setValorReal(Double ValorReal) {
        this.ValorReal = ValorReal;
    }

    public Double getValorVenta() {
        return ValorVenta;
    }

    public void setValorVenta(Double ValorVenta) {
        this.ValorVenta = ValorVenta;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public boolean isIsnacional() {
        return isnacional;
    }

    public void setIsnacional(boolean isnacional) {
        this.isnacional = isnacional;
    }
    
    
}
