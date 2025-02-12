package com.example.pokedex.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pokemon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pok")
    private Long pokeId;

    @Column(name = "nome_pok", nullable = false)
    private String pokeNome;

    @Column(name = "tipo_pok")
    private String pokeTipo;

    @Column(name = "tamanho")
    private double pokeTamanho;

    @Column(name = "peso")
    private double pokePeso;

    @Column(name = "bioma")
    private String pokeBioma;

    @ManyToMany
    @JsonIgnoreProperties("pokemons")
    @JoinTable(
                name = "Pokemon_Ataque",
                joinColumns = @JoinColumn(name = "id_pok"),
                inverseJoinColumns = @JoinColumn(name = "id_ataq")

            )
    private List<Ataque> ataques;

    @ManyToMany
    @JsonIgnoreProperties("pokemons")
    @JoinTable(
                name = "Pokemon_Treinador",
                joinColumns = @JoinColumn(name = "id_pok"),
                inverseJoinColumns = @JoinColumn(name = "id_tre")

            )
    private List<Treinador> treinadores;

    @ManyToMany
    @JsonIgnoreProperties("pokemons")
    @JoinTable(
                name = "Pokemon_Regiao",
                joinColumns = @JoinColumn(name = "id_pok"),
                inverseJoinColumns = @JoinColumn(name = "id_reg")

            )
    private List<Regiao> regioes;
}
