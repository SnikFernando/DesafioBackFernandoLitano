package com.desafioback.banca.repository;

import com.desafioback.banca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author GAMER HP
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT u FROM Usuario u WHERE u.correo =?1 AND u.contrasena =?2 ")
    public Usuario Login(String correo, String contraseña );
    
    @Query("SELECT u FROM Usuario u WHERE u.correo =?1 ")
    public Usuario getCorreo(String correo );
}
