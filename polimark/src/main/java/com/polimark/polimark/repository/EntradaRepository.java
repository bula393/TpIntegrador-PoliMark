package com.polimark.polimark.repository;

public class EntradaRepository JpaRepository<Entrada, Integer> {
    boolean existsByFuncionAndButaca(int idFuncion, Butaca butaca);
}
