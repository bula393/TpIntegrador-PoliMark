package com.api.Polimark.repository;
import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    boolean existsByFuncionIdFuncionAndButacaIdButaca(Integer funcionId, int butacaId);


    long countByFuncionIdFuncion(Integer funcionId);

    List<Entrada> findByCompraIdCompra(Integer compraId);

    // Contar entradas vendidas para una función
    long countByFuncionAndCompraPagadoTrue(Funcion funcion);

    // Verificar si existe entrada para función y butaca
    boolean existsByFuncionAndButacaAndCompraPagadoTrue(Funcion funcion, Butaca butaca);
}
