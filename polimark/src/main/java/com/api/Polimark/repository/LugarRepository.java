package com.api.Polimark.repository;

import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.modelo.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {

}