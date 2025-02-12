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
@Table(name = "Treinador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treinador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tre")
    private Long treinaId;

    @Column(name = "nome_tre")
    private String treinaNome;

    @Column(name = "cidade")
    private String cidade;

    @ManyToMany(mappedBy = "treinadores")
    @JsonIgnore
    List<Pokemon> pokemons;
}
