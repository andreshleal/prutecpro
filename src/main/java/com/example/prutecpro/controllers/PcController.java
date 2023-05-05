package com.example.prutecpro.controllers;

import com.example.prutecpro.entities.Pc;
import com.example.prutecpro.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pc")
public class PcController {

    @Autowired private PcService pcService;

    @GetMapping
    public ResponseEntity<List<Pc>> listarPcs(){
        return new ResponseEntity<>(pcService.listaPcs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pc> guardarPc(@RequestBody Pc pc){
        return new ResponseEntity<>(pcService.guardarPc(pc), HttpStatus.CREATED);
    }
}
