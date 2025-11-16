package com.api.Polimark.repository;
import com.api.Polimark.modelo.Producto;
import com.api.Polimark.modelo.ProductoHasCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(String categoria);
}
