package com.example.prutecpro.controllers;

import com.example.prutecpro.dtos.EncuestaDto;
import com.example.prutecpro.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encuesta")
public class EncuestaController {

    @Autowired private EncuestaService encuestaService;

    @GetMapping
    public ResponseEntity<List<EncuestaDto>> listaEncuestas(){
        return ResponseEntity.ok(encuestaService.listaEncuesta());
    }

    @PostMapping
    public ResponseEntity<EncuestaDto> guardarEncuesta(@RequestBody EncuestaDto encuestaDto){
        return new ResponseEntity<>(encuestaService.guardarEncuesta(encuestaDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEncuesta(@PathVariable("id") int id){
        encuestaService.eliminarEncuesta(id);
    }
}
