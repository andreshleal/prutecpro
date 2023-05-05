package com.example.prutecpro.service.impl;

import com.example.prutecpro.entities.Pc;
import com.example.prutecpro.repositories.PcRepository;
import com.example.prutecpro.service.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcServiceImpl implements PcService {

    @Autowired private PcRepository pcRepository;

    @Override
    public List<Pc> listaPcs() {
        return pcRepository.findAll();
    }

    @Override
    public Pc guardarPc(Pc pc) {
        return pcRepository.save(pc);
    }
}
