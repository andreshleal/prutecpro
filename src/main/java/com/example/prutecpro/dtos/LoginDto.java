package com.example.prutecpro.dtos;


public class LoginDto {


    private String nombreUsuarioOrEmail;


    private String password;


    public String getNombreUsuarioOrEmail() {
        return nombreUsuarioOrEmail;
    }

    public void setNombreUsuarioOrEmail(String nombreUsuarioOrEmail) {
        this.nombreUsuarioOrEmail = nombreUsuarioOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
