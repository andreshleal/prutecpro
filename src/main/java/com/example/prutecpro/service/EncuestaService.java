package com.example.prutecpro.service;

import com.example.prutecpro.dtos.EncuestaDto;


import java.util.List;

public interface EncuestaService {

    List<EncuestaDto> listaEncuesta();

    EncuestaDto guardarEncuesta(EncuestaDto encuestaDto);

    void eliminarEncuesta(int id);
}
