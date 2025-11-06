package com.api.Polimark.repository;

import com.api.Polimark.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, String> {

    // ✅ CORRECTO - métodos que usan propiedades de Pelicula
    List<Pelicula> findByNombre(String nombre);
    List<Pelicula> findByProductor(String productor);
    List<Pelicula> findByDuracionMinGreaterThan(Integer duracion);
    List<Pelicula> findByNombreContaining(String nombre);


}