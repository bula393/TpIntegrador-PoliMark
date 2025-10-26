package com.polimark.polimark.repository;

public class ButacaRepository extends JpaRepository<Butaca, Integer> {
    List<Butaca> findBySala(Sala sala);
}
