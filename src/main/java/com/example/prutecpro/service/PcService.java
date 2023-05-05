package com.example.prutecpro.service;

import com.example.prutecpro.entities.Pc;

import java.util.List;

public interface PcService {

    List<Pc> listaPcs();

    Pc guardarPc(Pc pc);
}
