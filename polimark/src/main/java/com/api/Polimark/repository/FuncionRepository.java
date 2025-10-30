package com.api.Polimark.repository;

import com.api.Polimark.modelo.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Integer> {
    // Solo métodos básicos, sin queries complejas
    List<Funcion> findAllByOrderByHorario();
}