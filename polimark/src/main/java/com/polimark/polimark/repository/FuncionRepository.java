package com.polimark.Polimark.repository;

import com.polimark.Polimark.modelo.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface FuncionRepository extends JpaRepository<Funcion, Integer> {

    @Query("""
        SELECT f FROM Funcion f
        WHERE 
            (:fecha IS NULL OR DATE(f.horario) = :fecha)
        AND (:sucursalNombre IS NULL OR f.sala.lugar.nombre = :sucursalNombre)
        AND (:peliculaNombre IS NULL OR f.pelicula.nombre = :peliculaNombre)
        ORDER BY f.horario
    """)
    List<Funcion> buscarFunciones(
            @Param("fecha") LocalDate fecha,
            @Param("sucursalNombre") String sucursalNombre,
            @Param("peliculaNombre") String peliculaNombre
    );
}

