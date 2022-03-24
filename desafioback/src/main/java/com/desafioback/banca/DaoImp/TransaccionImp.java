package com.desafioback.banca.DaoImp;

// @author GAMER HP

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafioback.banca.entities.Moneda;
import com.desafioback.banca.entities.TipoCambio;
import com.desafioback.banca.entities.Transaccion;
import com.desafioback.banca.entities.TransaccionDtoImp;
import com.desafioback.banca.entities.Usuario;

public class TransaccionImp  {
    
    public double Calculo(Moneda monedaorigen,Moneda monedadestino,TipoCambio tipo,Usuario usuario,TransaccionDtoImp transaccion){
        double total =0;
        String tipoCambio = tipo.getNombre();
        switch (tipoCambio) {
            case "compra":{ //modena extranjera a nacional
                double monto = transaccion.getMonto();
                double monedaOrigen = monedaorigen.getValorCompra();
                total = (monto * monedaOrigen);
                break;
            }              
            case "venta":{//nacional a extrajenra
                double monto = transaccion.getMonto();
                double monedaDestino= monedadestino.getValorVenta();
                total = (monto / monedaDestino);
                break;
            } 
            case "divisas":{//entre extranjeras
                double monto = transaccion.getMonto();
                double monedaOrigen = monedaorigen.getValorCompra();
                double monedaDestino= monedadestino.getValorReal();
                total = (monto * monedaOrigen)/monedaDestino;
                break;
            } 
            default:
                return total;
        }
        
        return total;
    }
    
    public long Decode(String token){
        String[] parts = token.split(" ");
        token = parts[1]; 
        Algorithm algorithm = Algorithm.HMAC256("PAASSSSWORR123");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("id").asLong();
    }
}
