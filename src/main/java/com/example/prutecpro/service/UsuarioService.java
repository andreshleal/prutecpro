package com.example.prutecpro.service;


import com.example.prutecpro.dtos.JwtAuthResponseDto;
import com.example.prutecpro.dtos.LoginDto;
import com.example.prutecpro.dtos.UsuarioDto;

import java.io.IOException;
import java.util.List;

public interface UsuarioService {

    String createUsuario(UsuarioDto usuarioDto) throws IOException;

    JwtAuthResponseDto autenticarUsuario(LoginDto loginDto);

    UsuarioDto getUserDataByToken(String token);

    List<UsuarioDto> getUsuarios();

}
