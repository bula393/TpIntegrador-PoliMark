package com.api.Polimark.repository;

import com.api.Polimark.modelo.Entrada;
import com.api.Polimark.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT e FROM Usuario u " +
            "JOIN u.compras c " +
            "JOIN c.entradas e " +
            "WHERE u.identificador = :idCliente")
    List<Entrada> findHistorialById(@Param("idCliente") Integer idCliente);
}
