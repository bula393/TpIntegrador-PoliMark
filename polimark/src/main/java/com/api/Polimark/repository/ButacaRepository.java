package com.api.Polimark.repository;

import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ButacaRepository extends JpaRepository<Butaca, Integer> {
    List<Butaca> findBySala(Sala sala);
}
