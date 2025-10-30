package com.api.Polimark.repository;
import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    boolean existsByFuncionAndButaca(Funcion funcion, Butaca butaca);


    long countByFuncionIdfuncion(Integer funcionId);

    List<Entrada> findByCompraId(Integer compraId);
}
