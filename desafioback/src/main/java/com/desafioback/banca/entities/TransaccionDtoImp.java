package com.desafioback.banca.entities;

// @author GAMER HP


public class TransaccionDtoImp {
    private long id;
    private String fecha;
    private double monto;
    private double montoCambio;
    private long idtipocambio;
    private long idmoneda_origen;
    private long idmoneda_destino;
    private long idusuario;

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

    public long getIdtipocambio() {
        return idtipocambio;
    }

    public void setIdtipocambio(long idtipocambio) {
        this.idtipocambio = idtipocambio;
    }

    public long getIdmoneda_origen() {
        return idmoneda_origen;
    }

    public void setIdmoneda_origen(long idmoneda_origen) {
        this.idmoneda_origen = idmoneda_origen;
    }

    public long getIdmoneda_destino() {
        return idmoneda_destino;
    }

    public void setIdmoneda_destino(long idmoneda_destino) {
        this.idmoneda_destino = idmoneda_destino;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }
    
    

}
