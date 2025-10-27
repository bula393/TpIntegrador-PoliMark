package com.polimark.Polimark.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.polimark.Polimark.modelo.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

}
