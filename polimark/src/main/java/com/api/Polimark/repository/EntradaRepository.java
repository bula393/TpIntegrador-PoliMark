package com.api.Polimark.repository;
import com.api.Polimark.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    boolean existsByFuncionAndButaca(Funcion funcion, Butaca butaca);

    // Método para contar entradas vendidas por función
    long countByFuncionIdfuncion(Integer funcionId);

}
