package com.example.pokedex.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ataque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ataque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ataq")
    private Long ataqId;

    @Column(name = "nome_ataq", nullable = false)
    private String ataqNome;

    @Column(name = "tipo_ataq")
    private String ataqTipo;

    @Column(name = "dano")
    private int dano;

    @Column(name = "precisao")
    private int precisao;

    @Column(name = "pp")
    private int pp;

    @ManyToMany(mappedBy = "ataques")
    @JsonIgnore
    List<Pokemon> pokemons;
}
