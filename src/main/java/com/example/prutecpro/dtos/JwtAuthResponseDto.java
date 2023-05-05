package com.example.prutecpro.dtos;

public class JwtAuthResponseDto {


    private String tokenAcceso;
    private String tipoToken;


    public JwtAuthResponseDto(String tokenAcceso, String tipoToken) {
        this.tokenAcceso = tokenAcceso;
        this.tipoToken = tipoToken;
    }

    public JwtAuthResponseDto(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTokenAcceso() {
        return tokenAcceso;
    }

    public void setTokenAcceso(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
}
