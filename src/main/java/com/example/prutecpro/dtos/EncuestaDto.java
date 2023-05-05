package com.example.prutecpro.dtos;


import java.util.Date;

public class EncuestaDto {

    public EncuestaDto() {
    }

    public EncuestaDto(int id, int documento, String email, String comentarios, String marcaFavorita, Date createdAt) {
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.comentarios = comentarios;
        this.marcaFavorita = marcaFavorita;
        this.createdAt = createdAt;
    }



    private int id;
    private int documento;
    private String email;
    private String comentarios;
    private String marcaFavorita;

    private Date createdAt;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
