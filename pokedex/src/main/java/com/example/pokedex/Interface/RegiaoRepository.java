package com.example.pokedex.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pokedex.Entity.Regiao;

@Repository
public interface RegiaoRepository extends JpaRepository<Long, Regiao>{
    
}
