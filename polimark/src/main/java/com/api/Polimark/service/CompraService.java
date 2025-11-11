package com.api.Polimark.service;

import com.api.Polimark.dto.*;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    private final EntradaService entradaService;
    private final EntradaRepository entradaRepository;
    private final FuncionRepository funcionRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final CompraRepository compraRepository;
    private final ArticuloRepository articuloRepository;
    private final ButacaRepository butacaRepository;
    private final CompraHasPromocionRepository compraHasPromocionRepository;

    public CompraService(EntradaService entradaService, EntradaRepository entradaRepository, FuncionRepository funcionRepository,
                         MetodoPagoRepository metodoPagoRepository, CompraRepository compraRepository, ArticuloRepository articuloRepository, ButacaRepository butacaRepository, CompraHasPromocionRepository compraHasPromocionRepository)
        {
            this.entradaService = entradaService;
            this.entradaRepository = entradaRepository;
            this.funcionRepository = funcionRepository;
            this.metodoPagoRepository = metodoPagoRepository;
            this.compraRepository = compraRepository;
            this.articuloRepository = articuloRepository;
            this.butacaRepository = butacaRepository;
            this.compraHasPromocionRepository = compraHasPromocionRepository;
        }
    public Compra reservarCompra(int idFuncion, int idCompra,List<Integer> articulosId, List<Integer> butacasid) {
        Funcion funcion = funcionRepository.findById(idFuncion)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));
        if (butacasid.size() == articulosId.size()) {}
            if(butacasid.size() > 6){
                int contador = 0;
                Compra nuevaCompra = compraRepository.findById(idCompra)
                        .orElseThrow(() -> new RuntimeException("compra no encontrada"));;
                for(Integer butacaId : butacasid){
                        Articulo articulo = articuloRepository.findById(articulosId.get(contador))
                                .orElseThrow(() -> new RuntimeException("articulo no encontrado"));

                        Butaca butaca = butacaRepository.findById(butacaId)
                                .orElseThrow(() -> new RuntimeException("butaca no encontrado"));
                        entradaService.reservarAsiento(nuevaCompra,articulo,funcion,butaca);
                        contador++;
                    }
                return nuevaCompra;
            }
            else{
                throw new excedeLimiteExeption();
            }

    }

    public ResumenCompra generarResumenCompra(int idCompra){
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("compra no encontrada"));



        List<ButacaVisible> butacas = List.of();
        List<Entrada> entradas = entradaRepository.findByCompra_idCompra(idCompra);
        Funcion funcionCompra = entradas.get(0).getFuncion();
        List<CompraHasPromocion> compraHasPromocions = compraHasPromocionRepository.findByCompra_idCompra(idCompra);
        FuncionVisible  funcionVisible = new FuncionVisible(funcionCompra.getIdFuncion(),funcionCompra.getHorario(),new SalaVisible(funcionCompra.getSala().getIdSala(),funcionCompra.getSala().getCapacidad(),funcionCompra.getSala().getTipo(),funcionCompra.getSala().getLugar().getNombre()),funcionCompra.getPelicula());
        ResumenCompra resumenCompra = new ResumenCompra(butacas,calcularTotal(compra),entradas,,funcionVisible);

        return resumenCompra;
    }

    public static int calcularTotal(Compra compra){
        int total = 0;
        return total;
    }

    public ResumenCompra pagarCompra(int idCompra,int idMetodoPago) {
        Optional<Compra> compraOptional = compraRepository.findById(idCompra);
        Compra compra = compraOptional.get();
        MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
        .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));
        compra.setMetodoPago(metodoPago);
        compra.setPagado(true);
        compraRepository.save(compra);
        return generarResumenCompra(idCompra);
    }
}
