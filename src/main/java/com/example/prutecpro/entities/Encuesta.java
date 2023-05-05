package com.example.prutecpro.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "encuestas")
public class Encuesta {

    public Encuesta() {
    }

    public Encuesta(int documento, String email, String comentarios, String marcaFavorita) {
        this.documento = documento;
        this.email = email;
        this.comentarios = comentarios;
        this.marcaFavorita = marcaFavorita;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int documento;
    private String email;
    private String comentarios;
    private String marcaFavorita;

    @CreationTimestamp
    private Date createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getMarcaFavorita() {
        return marcaFavorita;
    }

    public void setMarcaFavorita(String marcaFavorita) {
        this.marcaFavorita = marcaFavorita;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
