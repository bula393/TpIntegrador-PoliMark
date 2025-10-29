package com.api.Polimark.repository;

import com.api.Polimark.modelo.Funcion;
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

    @Query("""
        SELECT 
            NEW com.api.Polimark.dto.RangoHorario(
                FUNCTION('TIME', CONCAT(LPAD(FUNCTION('HOUR', f.horario), 2, '0'), ':00:00')),
                FUNCTION('TIME', CONCAT(LPAD(FUNCTION('HOUR', f.horario), 2, '0'), ':59:59'))
            ),
            AVG(CAST((
                SELECT COUNT(e) 
                FROM Entrada e 
                WHERE e.funcion.idfuncion = f.idfuncion
            ) as DOUBLE) / CAST(f.sala.capacidad as DOUBLE)) * 100
        FROM Funcion f
        WHERE DATE(f.horario) BETWEEN :desde AND :hasta
        AND (:nombreEstablecimiento IS NULL OR f.sala.lugar.nombre = :nombreEstablecimiento)
        GROUP BY FUNCTION('HOUR', f.horario)
        ORDER BY FUNCTION('HOUR', f.horario)
    """)
    List<Object[]> findOcupacionPorRangoHorario(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta,
            @Param("nombreEstablecimiento") String nombreEstablecimiento
    );
}

