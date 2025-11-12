package com.api.Polimark.controler;

import com.api.Polimark.dto.Perfil;
import com.api.Polimark.dto.UsuarioRequest;
import com.api.Polimark.modelo.Rango;
import com.api.Polimark.modelo.Usuario;
import com.api.Polimark.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class UsuarioControler {

    private final UsuarioService usuarioService;

    public UsuarioControler(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("{id}/perfil")
    public Perfil perfil(@PathVariable int idCliente) {
        return usuarioService.obtenerPerfil(idCliente);
    }

    @PostMapping("crearUsuario")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequest usuario) {
        try {
            return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Json para crear usuario
//    {
//        "identificador": 0,
//            "nombre": "María",
//            "apellido": "González",
//            "mail": "maria.gonzalez@ejemplo.com",
//            "contrasena": "miPassword123",
//            "puntos": 0,
//            "rangoId": 1
//    }




    @GetMapping("/{idCliente}/logIn")
    public ResponseEntity<?> logIn(@PathVariable int idCliente, @RequestBody String contrasenia) {
        try {
            return ResponseEntity.ok(usuarioService.usuarioLogueado(idCliente,contrasenia));
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }
// json para loguearse
//    {
//            "contrasena": "miPassword123",
//    }

    @PutMapping("/{id}/adquirirRango")
    public ResponseEntity<?> adquirirRango(@PathVariable int idCliente, @RequestBody Rango rango) {
        try {
            return ResponseEntity.ok(usuarioService.darRangoCliente(idCliente,rango));
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }

}

