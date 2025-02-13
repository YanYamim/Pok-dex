package com.example.pokedex.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pokedex.Entity.Treinador;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long>{

    
}