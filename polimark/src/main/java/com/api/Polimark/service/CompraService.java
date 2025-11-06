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
    private final ArticuloRepository articuloRepository;

    public CompraService(EntradaService entradaService, FuncionRepository funcionRepository, UsuarioRepository usuarioRepository,
                         MetodoPagoRepository metodoPagoRepository, CompraRepository compraRepository, ArticuloRepository articuloRepository)
        {
            this.entradaService = entradaService;
            this.funcionRepository = funcionRepository;
            this.usuarioRepository = usuarioRepository;
            this.metodoPagoRepository = metodoPagoRepository;
            this.compraRepository = compraRepository;
            this.articuloRepository = articuloRepository;
        }
//    public Compra reservarCompra(int idFuncion, int identificadorUsuario,List<Articulo> articulos, List<Butaca> butacas) {
//        Funcion funcion = funcionRepository.findById(idFuncion)
//                .orElseThrow(() -> new RuntimeException("Función no encontrada"));
//
//        Usuario usuario = usuarioRepository.findById(identificadorUsuario)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//
//
//        if (butacas.size() == articulos.size()) {}
//            if(butacas.size() > 6){
//                int contador = 0;
//                Compra nuevaCompra = new Compra(false, null, usuario);
//                for(Butaca butaca : butacas){
//                        entradaService.reservarAsiento(nuevaCompra,articulos.get(contador),funcion,butaca);
//                        contador++;
//                    }
//                compraRepository.save(nuevaCompra);
//                return nuevaCompra;
//            }
//            else{
//                throw new excedeLimiteExeption();
//            }
//
//    }

//    public void pagarCompra(int idCompra,int idMetodoPago) {
//        Compra compra = compraRepository.findById(idCompra);
//        MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
//        .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));
//
//
//    }
}
