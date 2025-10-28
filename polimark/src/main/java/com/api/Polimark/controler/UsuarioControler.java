package com.api.Polimark.controler;

import com.api.Polimark.dto.ButacaEstado;
import com.api.Polimark.dto.Perfil;
import com.api.Polimark.service.ButacaService;
import com.api.Polimark.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class UsuarioControler {

    private final UsuarioService usuarioService;

    public UsuarioControler(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}/perfil")
    public Perfil perfil(@PathVariable int idCliente) {
        return usuarioService.obtenerPerfil(idCliente);
    }
}

