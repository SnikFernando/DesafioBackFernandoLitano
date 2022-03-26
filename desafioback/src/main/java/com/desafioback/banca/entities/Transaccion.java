package com.desafioback.banca.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
// @author GAMER HP

@Entity
@Table()
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fecha;
    private double monto;
    private double montoCambio;
    
    @OneToOne()    
    @JoinColumn(name = "tipo_id")
    private TipoCambio tipocambio;
    
    @OneToOne()    
    @JoinColumn(name = "moneda_origen_id")
    private Moneda moneda_origen;
    @OneToOne()    
    @JoinColumn(name = "moneda_destino_id")
    private Moneda moneda_destino;

    @OneToOne()    
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Transaccion() {
    }

    public Transaccion(long id, String fecha, double monto, double montoCambio, TipoCambio tipocambio, Moneda moneda_origen, Moneda moneda_destino, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.montoCambio = montoCambio;
        this.tipocambio = tipocambio;
        this.moneda_origen = moneda_origen;
        this.moneda_destino = moneda_destino;
        this.usuario = usuario;
    }

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMontoCambio() {
        return montoCambio;
    }

    public void setMontoCambio(double montoCambio) {
        this.montoCambio = montoCambio;
    }

    public TipoCambio getTipocambio() {
        return tipocambio;
    }

    public void setTipocambio(TipoCambio tipocambio) {
        this.tipocambio = tipocambio;
    }

    public Moneda getMoneda_origen() {
        return moneda_origen;
    }

    public void setMoneda_origen(Moneda moneda_origen) {
        this.moneda_origen = moneda_origen;
    }

    public Moneda getMoneda_destino() {
        return moneda_destino;
    }

    public void setMoneda_destino(Moneda moneda_destino) {
        this.moneda_destino = moneda_destino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
    
}
