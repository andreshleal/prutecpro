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


        usuarioDto.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        usuarioDto.setRoles(Collections.singleton(rolRepository.findRolByNombre("ROLE_USER").get()));
        usuarioRepository.save(toEntity(usuarioDto));

        return "Usuario registrado correctamente";
    }

    @Override
    public JwtAuthResponseDto autenticarUsuario(LoginDto loginDto) {

        Usuario validarUuario = usuarioRepository.findUsuarioByNombreUsuarioOrEmail(
                loginDto.getNombreUsuarioOrEmail(),
                loginDto.getNombreUsuarioOrEmail()
        ).get();

        if(validarUuario.isEstado()){
            return new JwtAuthResponseDto(null, "usuaio bloqueado");
        }



        String token = null;
        try {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getNombreUsuarioOrEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

            Usuario usuario = usuarioRepository.findUsuarioByNombreUsuarioOrEmail(
                    loginDto.getNombreUsuarioOrEmail(),
                    loginDto.getNombreUsuarioOrEmail()
            ).get();
            usuario.setIntentos(0);
            usuarioRepository.save(usuario);

        token = jwtTokenProvider.generarToken(authentication);

        }catch (Exception e){
            System.out.println("error al auteticar");
            Usuario usuario = usuarioRepository.findUsuarioByNombreUsuarioOrEmail(
                    loginDto.getNombreUsuarioOrEmail(),
                    loginDto.getNombreUsuarioOrEmail()
                    ).get();
            usuario.setIntentos(usuario.getIntentos() + 1);
            if(usuario.getIntentos() >= 3){
                usuario.setEstado(true);
            }
            usuarioRepository.save(usuario);
        }



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

    @Override
    public UsuarioDto buscarPorCorreo(String correo) {
        return toDto(usuarioRepository.findUsuarioByEmail(correo).get());
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
