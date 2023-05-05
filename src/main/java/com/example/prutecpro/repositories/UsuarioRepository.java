package com.example.prutecpro.repositories;


import com.example.prutecpro.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findUsuarioByNombreUsuarioOrEmail(String nombre, String email);
    Optional<Boolean> existsUsuarioByNombreUsuario(String nombreUsuario);
    Optional<Boolean> existsUsuarioByEmail(String email);


}
