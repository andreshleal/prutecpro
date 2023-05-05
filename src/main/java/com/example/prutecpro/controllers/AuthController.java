package com.example.prutecpro.controllers;


import com.example.prutecpro.dtos.JwtAuthResponseDto;
import com.example.prutecpro.dtos.LoginDto;
import com.example.prutecpro.dtos.UsuarioDto;
import com.example.prutecpro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(
            @RequestBody UsuarioDto usuarioDto) throws IOException
    {
        return new ResponseEntity<>(usuarioService.createUsuario(usuarioDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> loginUsuarion(
            @RequestBody LoginDto loginDto){
        return new ResponseEntity<>(usuarioService.autenticarUsuario(loginDto), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDto> getNombreusuarioByToken(
            @RequestHeader(name = "token") String token
    ){
        return ResponseEntity.ok().body(usuarioService.getUserDataByToken(token));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDto>> getUsuarios(){
        return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
    }

}
