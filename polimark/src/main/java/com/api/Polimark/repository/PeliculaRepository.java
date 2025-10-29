package com.api.Polimark.repository;

import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, String> {

    // Nota: El ID de Pelicula es String (nombre), no Integer

    @Query("SELECT p FROM Pelicula p " +
            "WHERE p.nombre = (" +
            "    SELECT f.pelicula.nombre FROM Funcion f " +
            "    JOIN f.entradas e " +
            "    JOIN e.compra c " +
            "    WHERE DATE(f.horario) BETWEEN :desde AND :hasta " +
            "    AND c.pagado = true " +
            "    GROUP BY f.pelicula.nombre " +
            "    ORDER BY COUNT(e) DESC " +
            "    LIMIT 1" +
            ")")
    Optional<Pelicula> findPeliculaMasTaquilleraEntre(@Param("desde") LocalDate desde,
                                                      @Param("hasta") LocalDate hasta);


    @Query("SELECT NEW com.api.Polimark.dto.RangoHorario(" +
            "FUNCTION('DATE_FORMAT', f.horario, '%H:00:00'), " +
            "FUNCTION('DATE_FORMAT', FUNCTION('DATE_ADD', f.horario, FUNCTION('INTERVAL 1 HOUR')), '%H:00:00')) " +
            ") " +
            "FROM Funcion f " +
            "JOIN f.entradas e " +
            "JOIN e.compra c " +
            "WHERE f.pelicula.nombre = :peliculaNombre " +
            "AND DATE(f.horario) BETWEEN :desde AND :hasta " +
            "AND c.pagado = true " +
            "GROUP BY FUNCTION('HOUR', f.horario) " +
            "ORDER BY COUNT(e) DESC " +
            "LIMIT 1")
    Optional<RangoHorario> rangoHorarioMasVistaEntre(@Param("desde") LocalDate desde,
                                                     @Param("hasta") LocalDate hasta,
                                                     @Param("peliculaNombre") String nombrePelicula);


}