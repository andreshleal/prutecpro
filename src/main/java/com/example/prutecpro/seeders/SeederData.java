package com.example.prutecpro.seeders;


import com.example.prutecpro.entities.Pc;
import com.example.prutecpro.entities.Rol;
import com.example.prutecpro.entities.Usuario;
import com.example.prutecpro.repositories.PcRepository;
import com.example.prutecpro.repositories.RolRepository;
import com.example.prutecpro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class SeederData {

    @Autowired private RolRepository rolRepository;

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private PcRepository pcRepository;



    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent){
        if (rolRepository.findAll().isEmpty()) createRoles();
        if (usuarioRepository.findAll().isEmpty()) createUsuarios();
        if (pcRepository.findAll().isEmpty()) createPcs();

    }

    public void createRoles(){
        List<Rol> roles = new ArrayList<>();
        roles.add(new Rol("ROLE_USER","rol para usuarios tipo clientes, sin acceso al CMS"));
        roles.add(new Rol("ROLE_ADMIN","rol para usuario tipo administrador, acceso al CMS"));
        rolRepository.saveAll(roles);
    }

    public void createUsuarios(){

        List<Rol> roles = rolRepository.findAll();

        List<Usuario> usuarios = new ArrayList<>();

        Set<Rol> setRolesUser = new HashSet<>();
        setRolesUser.add(roles.get(0));
        Set<Rol> setRolesAdmin = new HashSet<>();
        setRolesAdmin.add(roles.get(1));



        // rol user
        usuarios.add(new Usuario("andres","andres",
                "andres@mail.com",passwordEncoder.encode("123456"),setRolesUser));



        // rol user and admin
        usuarios.add(new Usuario("admin","admin",
                "admin@mail.com",passwordEncoder.encode("123456"),setRolesAdmin));

        usuarioRepository.saveAll(usuarios);
    }

    public void createPcs(){
        List<Pc> pcs = new ArrayList<>();
        pcs.add(new Pc("Lenovo"));
        pcs.add(new Pc("Asus"));
        pcs.add(new Pc("Acer"));
        pcs.add(new Pc("Apple"));
        pcs.add(new Pc("Msi"));

        pcRepository.saveAll(pcs);
    }








}
