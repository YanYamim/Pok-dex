package com.example.pokedex.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pokedex.Entity.Ataque;

@Repository
public interface AtaqueRepository extends JpaRepository<Ataque, Long> {
    
}
