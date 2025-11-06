package com.api.Polimark.repository;

import com.api.Polimark.modelo.Entrada;
import com.api.Polimark.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByIdentificador(int identificador);

    // Encuentra usuario por email
    Usuario findByMail(String mail);

    // Encuentra usuarios por nombre
    List<Usuario> findByNombre(String nombre);

    // Encuentra usuarios por rango
    List<Usuario> findByRangoIdRango(int rangoId);
}
