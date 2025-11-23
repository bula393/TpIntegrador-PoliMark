package com.api.Polimark.controler;

import com.api.Polimark.dto.ButacaEstado;
import com.api.Polimark.service.ButacaService;
import com.api.Polimark.service.LugarService;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lugar")
@CrossOrigin(origins = "*")
public class LugarController {

    private final LugarService lugarService;


    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @GetMapping("nombresSedes")
    public ArrayList<String> obtenerNombresSedes() {
        return lugarService.nombresSedes();
    }
}

