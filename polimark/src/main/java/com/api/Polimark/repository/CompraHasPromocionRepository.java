package com.api.Polimark.repository;


import com.api.Polimark.modelo.*;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface CompraHasPromocionRepository extends JpaRepository<CompraHasPromocion, Integer> {
    List<CompraHasPromocion> findByCompra_Idcompra(Integer idcompra);
}
