package com.api.Polimark.service;

import com.api.Polimark.dto.PeliculaHorario;
import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public PeliculaHorario peliculaMasTaquillera(LocalDate desde, LocalDate hasta) {
        Optional<Pelicula> peliculaMasTaquillera = peliculaRepository.findPeliculaMasTaquilleraEntre(desde,hasta);

        Optional<RangoHorario> rangoHorarioMasVista = peliculaRepository.rangoHorarioMasVistaEntre(desde,hasta,peliculaMasTaquillera.get().getNombre());

        return new PeliculaHorario(peliculaMasTaquillera.get(),rangoHorarioMasVista.get());


    }
}
