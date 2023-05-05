package com.example.prutecpro.service.impl;

import com.example.prutecpro.dtos.EncuestaDto;
import com.example.prutecpro.entities.Encuesta;
import com.example.prutecpro.repositories.EncuestaRepository;
import com.example.prutecpro.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    @Autowired private EncuestaRepository encuestaRepository;

    @Override
    public List<EncuestaDto> listaEncuesta() {
        List<Encuesta> encuestas = encuestaRepository.findAll();
        return encuestas.stream().map(encuesta -> entityToDto(encuesta))
                .collect(Collectors.toList());
    }

    @Override
    public EncuestaDto guardarEncuesta(EncuestaDto encuestaDto) {
        return entityToDto( encuestaRepository.save(dtoToEntity(encuestaDto)) );
    }

    @Override
    public void eliminarEncuesta(int id) {
        encuestaRepository.deleteById(id);
    }



    private EncuestaDto entityToDto(Encuesta encuesta){
        return new EncuestaDto(
                encuesta.getId(),
                encuesta.getDocumento(),
                encuesta.getEmail(),
                encuesta.getComentarios(),
                encuesta.getMarcaFavorita(),
                encuesta.getCreatedAt()
        );
    }

    private Encuesta dtoToEntity(EncuestaDto encuestaDto){
        return new Encuesta(
                encuestaDto.getDocumento(),
                encuestaDto.getEmail(),
                encuestaDto.getComentarios(),
                encuestaDto.getMarcaFavorita()
        );
    }
}
