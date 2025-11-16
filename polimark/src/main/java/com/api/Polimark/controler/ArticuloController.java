package com.api.Polimark.controler;

import com.api.Polimark.service.ArticuloService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }


}