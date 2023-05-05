package com.example.prutecpro.service.impl;


import com.example.prutecpro.dtos.JwtAuthResponseDto;
import com.example.prutecpro.dtos.LoginDto;
import com.example.prutecpro.dtos.UsuarioDto;
import com.example.prutecpro.entities.Usuario;
import com.example.prutecpro.repositories.RolRepository;
import com.example.prutecpro.repositories.UsuarioRepository;
import com.example.prutecpro.security.JwtTokenProvider;
import com.example.prutecpro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RolRepository rolRepository;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenProvider jwtTokenProvider;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public String createUsuario(UsuarioDto usuarioDto) throws IOException {


//        if (usuarioRepository.existsUsuarioByNombreUsuario(usuarioDto.getNombreUsuario()).get()){
//            throw new IntegrationViolationName("Usuario","nombre de usuario",
//                    usuarioDto.getNombreUsuario());
//        }

//        if (usuarioRepository.existsUsuarioByEmail(usuarioDto.getEmail()).get()){
//            throw new IntegrationViolationName("Usuario","email"
//                    , usuarioDto.getEmail());
//        }

        usuarioDto.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        usuarioDto.setRoles(Collections.singleton(rolRepository.findRolByNombre("ROLE_USER").get()));
        usuarioRepository.save(toEntity(usuarioDto));

        return "Usuario registrado correctamente";
    }

    @Override
    public JwtAuthResponseDto autenticarUsuario(LoginDto loginDto) {

//        if (!usuarioRepository.findUsuarioByNombreUsuarioOrEmail(loginDto.getNombreUsuarioOrEmail(),
//                loginDto.getNombreUsuarioOrEmail()).isPresent())
//        {
//            throw new ResourceNotFoundException("Usuario","usuario o email",loginDto.getNombreUsuarioOrEmail());
//        }


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getNombreUsuarioOrEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generarToken(authentication);

        return new JwtAuthResponseDto(token, "Bearer");
    }

    @Override
    public UsuarioDto getUserDataByToken(String token) {
        String nombreUsuario = jwtTokenProvider.obtenerUsernameDelToken(token.substring(7));
        return toDto(usuarioRepository
                .findUsuarioByNombreUsuarioOrEmail(nombreUsuario, nombreUsuario).get());
    }

    @Override
    public List<UsuarioDto> getUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(user -> toDto(user)).collect(Collectors.toList());
    }


    private Usuario toEntity(UsuarioDto usuarioDto){
        return new Usuario(
                usuarioDto.getNombre(),
                usuarioDto.getNombreUsuario(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword(),
                usuarioDto.getRoles()
        );
    }

    private UsuarioDto toDto(Usuario usuario){
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getCreateAt(),
                usuario.getUpdateAt(),
                usuario.getRoles()
        );
    }


}
