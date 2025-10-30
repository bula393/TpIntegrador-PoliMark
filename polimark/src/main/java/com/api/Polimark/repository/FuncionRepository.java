package com.api.Polimark.repository;

import com.api.Polimark.modelo.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Integer> {
    // Solo métodos básicos, sin queries complejas
    List<Funcion> findAllByOrderByHorario();

    List<Funcion> findByPelicula_Id(Long idPelicula);
    List<Funcion> findByPelicula_IdAndFechaHoraBetween(Long idPelicula, LocalDateTime inicio, LocalDateTime fin);

    List<Funcion> findDistinctByFechaHoraAfter(LocalDateTime fechaHora);
}