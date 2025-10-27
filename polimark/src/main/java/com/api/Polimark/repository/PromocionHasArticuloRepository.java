package com.api.Polimark.repository;
import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PromocionHasArticuloRepository extends JpaRepository<PromocionHasArticulo, Integer> {
}
