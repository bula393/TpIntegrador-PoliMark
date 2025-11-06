package com.api.Polimark.repository;

import com.api.Polimark.modelo.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Integer> {

    // ✅ CORRECTO - métodos que usan propiedades de Funcion y sus relaciones
    List<Funcion> findByPeliculaNombreAndHorarioBetween(String nombrePelicula, LocalDateTime inicio, LocalDateTime fin);

    List<Funcion> findDistinctByHorarioAfter(LocalDateTime fechaHora);

    List<Funcion> findByPeliculaNombreAndHorarioAfter(String nombrePelicula, LocalDateTime fechaHora);

    List<Funcion> findBySalaIdSalaAndHorarioAfter(Integer idSala, LocalDateTime fechaHora);

    List<Funcion> findByPeliculaProductorAndHorarioAfter(String productor, LocalDateTime fechaHora);

    List<Funcion> findByPeliculaNombre(String nombrePelicula);

    List<Funcion> findAllByOrderByHorario();
}