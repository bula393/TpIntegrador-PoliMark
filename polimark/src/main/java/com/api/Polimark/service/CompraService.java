package com.api.Polimark.service;

import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    private final EntradaService entradaService;
    private final FuncionRepository funcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final CompraRepository compraRepository;

    public CompraService(FuncionRepository funcionRepository,UsuarioRepository usuarioRepository
    MetodoPagoRepository metodoPagoRepository,CompraRepository compraRepository)
        {
            this.funcionRepository = funcionRepository;
            this.usuarioRepository = usuarioRepository;
            this.metodoPagoRepository = metodoPagoRepository;
            this.compraRepository = compraRepository;
        }
    public void reservarCompra(int idFuncion, int identificadorUsuario, List<Butaca> butacas) {
        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        Usuario usuario = usuarioRepository.findById(identificadorUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(idmetodoPago)
        .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));


        if(butacas.size() > 6){
            Compra nuevaCompra = new Compra(false, null, usuario);
            for(Butaca butaca : butacas){
                    entradaService.reservarAsiento(nuevaCompra,funcion,butaca);
                }
            compraRepository.save(nuevaCompra);
        }
        else{
            throw new excedeLimiteExeption();
        }
    }

    public void pagarCompra() {
        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        Usuario usuario = usuarioRepository.findById(identificadorUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(idmetodoPago)
        .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));


    }
}
