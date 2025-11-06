package com.api.Polimark.repository;

import com.api.Polimark.modelo.*;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    List<Compra> findByUsuarioIdentificador(int usuarioIdentificador);

}