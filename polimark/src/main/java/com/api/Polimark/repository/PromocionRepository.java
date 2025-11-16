package com.api.Polimark.repository;

import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    List<Promocion> findByTipo(String tipo);
}
