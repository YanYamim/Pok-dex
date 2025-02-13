package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Pokemon;
import com.example.pokedex.Interface.PokemonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PokemonService {
    
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon registrarPokemon(String pokeNome, String pokeTipo, double pokeTamanho, double pokePeso, String pokeBioma) {
        Pokemon novoPokemon = new Pokemon();
        novoPokemon.setPokeNome(pokeNome);
        novoPokemon.setPokeTipo(pokeTipo);
        novoPokemon.setPokeTamanho(pokeTamanho);
        novoPokemon.setPokePeso(pokePeso);
        novoPokemon.setPokeBioma(pokeBioma);
        Pokemon pokemonSalvo = pokemonRepository.save(novoPokemon);
        return pokemonSalvo;
    }

    public List<Pokemon> listarPokemons() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons;
    }

    public Pokemon editarPokemon(@PathVariable Long pokeId, String pokeNome, String pokeTipo, double pokeTamanho, double pokePeso, String pokeBioma) {
        Optional<Pokemon> pokemonExistente = pokemonRepository.findById(pokeId);

        if(pokemonExistente.isPresent()) {
            Pokemon pokemonParaEditar = pokemonExistente.get();
            pokemonParaEditar.setPokeNome(pokeNome);
            pokemonParaEditar.setPokeTipo(pokeTipo);
            pokemonParaEditar.setPokeTamanho(pokeTamanho);
            pokemonParaEditar.setPokePeso(pokePeso);
            pokemonParaEditar.setPokeBioma(pokeBioma);
            return pokemonRepository.save(pokemonParaEditar);
        }

        throw new EntityNotFoundException("Pokémon não encontrado" + pokeId);
    }
}
