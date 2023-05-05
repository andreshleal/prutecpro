package com.example.prutecpro.dtos;


import com.example.prutecpro.entities.Rol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;
import java.util.Set;

public class UsuarioDto {

    public UsuarioDto(Long id, String nombre, String nombreUsuario, String email, Date createAt, Date updateAt, Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.roles = roles;
    }

    private Long id;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private Date createAt;

    private Date updateAt;

    @JsonIgnoreProperties("deleted")
    private Set<Rol> roles;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
