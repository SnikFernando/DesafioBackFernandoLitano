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
import java.util.Calendar;
import java.util.Date;

public class TransaccionImp  {
    private static String Password = "PAASSSSWORR123";
    
    public double Calculo(Moneda monedaorigen,Moneda monedadestino,TipoCambio tipo,Usuario usuario,TransaccionDtoImp transaccion){
        double total =0;
        String NacOrigen= tipo.getNacOrigen();
        String NacDestino= tipo.getNacDestino();
        
        if (NacOrigen.equals("Ex") && NacDestino.equals("Na")) {
            double monto = transaccion.getMonto();
            double monedaOrigen = monedaorigen.getValorCompra();
            total = (monto * monedaOrigen);

        } else if (NacOrigen.equals("Na") && NacDestino.equals("Ex")) {
            double monto = transaccion.getMonto();
            double monedaDestino = monedadestino.getValorVenta();
            total = (monto / monedaDestino);
        } else if (NacOrigen.equals("Ex") && NacDestino.equals("Ex")){
            double monto = transaccion.getMonto();
            double monedaOrigen = monedaorigen.getValorCompra();
            double monedaDestino = monedadestino.getValorReal();
            total = (monto * monedaOrigen) / monedaDestino;
        }
        return total;
        
        
    }
    
    public long Decode(String token){
        String[] parts = token.split(" ");
        token = parts[1]; 
        Algorithm algorithm = Algorithm.HMAC256(Password);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("id").asLong();
    }
    
    public String Encode(Usuario save){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(Password);
            Date fecha_expiracion = new Date();
            Calendar calendar = Calendar.getInstance();	
            calendar.setTime(fecha_expiracion); // Configuramos la fecha que se recibe	
            calendar.add(Calendar.MINUTE, 2); //Agregamos en cuanto deseamos que expire
            fecha_expiracion.setTime(calendar.getTimeInMillis());
            token = JWT.create()
                    .withClaim("id", save.getId())
                    .withClaim("dni", save.getDni())
                    .withIssuer("auth0")
                    .withExpiresAt(fecha_expiracion)
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return token;
        }
        
        
    }
}
