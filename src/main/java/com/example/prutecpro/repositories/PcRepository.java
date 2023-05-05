package com.example.prutecpro.repositories;

import com.example.prutecpro.entities.Pc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcRepository extends JpaRepository<Pc, Integer> {
}
