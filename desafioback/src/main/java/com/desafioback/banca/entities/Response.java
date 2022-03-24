package com.desafioback.banca.entities;

// @author GAMER HP
public class Response {
    private int codestado;
    private String estado;
    private String mensaje;
    private String token;
    private Object Data;

    public Response() {
    }

    public Response(int codestado, String estado, String mensaje, String token, Object Data) {
        this.codestado = codestado;
        this.estado = estado;
        this.mensaje = mensaje;
        this.token = token;
        this.Data = Data;
    }

    

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }

    public int getCodestado() {
        return codestado;
    }

    public void setCodestado(int codestado) {
        this.codestado = codestado;
    }
    
    

}
