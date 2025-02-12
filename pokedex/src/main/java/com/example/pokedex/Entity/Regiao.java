package com.example.pokedex.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Regiao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Regiao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg")
    private Long regId;

    @Column(name = "nome_reg")
    private String regNome;

    @ManyToMany(mappedBy = "regioes")
    @JsonIgnore
    List<Pokemon> pokemons;
}
