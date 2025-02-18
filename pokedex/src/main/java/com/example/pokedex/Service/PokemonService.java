package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Pokemon;
import com.example.pokedex.Interface.PokemonRepository;
import com.example.pokedex.err.BadRequestException;
import com.example.pokedex.err.InternalErrorException;
import com.example.pokedex.err.NotFoundException;

@Service
public class PokemonService {
    
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon registrarPokemon(String pokeNome, String pokeTipo, double pokeTamanho, double pokePeso, String pokeBioma) {
        try {
            Pokemon novoPokemon = new Pokemon();
            novoPokemon.setPokeNome(pokeNome);
            novoPokemon.setPokeTipo(pokeTipo);
            novoPokemon.setPokeTamanho(pokeTamanho);
            novoPokemon.setPokePeso(pokePeso);
            novoPokemon.setPokeBioma(pokeBioma);
            Pokemon pokemonSalvo = pokemonRepository.save(novoPokemon);
            return pokemonSalvo;
        } catch(Exception e) {
            throw new BadRequestException("Requisição incompleta, o Pokémon precisa ter um nome: " + e.getMessage());
        }
    }

    public List<Pokemon> listarPokemons() {
        try{ 
            return pokemonRepository.findAll();
        } catch(Exception e) {
            throw new InternalErrorException("Erro ao listar Pokémons" + e.getMessage());
        }
    }

    public Pokemon listarPokemonPorId(Long pokeId) {
        Optional<Pokemon> pokemonPorId = pokemonRepository.findById(pokeId);
            
        if(pokemonPorId.isPresent()) {
            return pokemonPorId.get();
        }
            
        throw new NotFoundException("Pokémon de Id " + pokeId + "não encontrado");
    }
    

    public Pokemon editarPokemon(@PathVariable Long pokeId, String pokeNome, String pokeTipo, double pokeTamanho, double pokePeso, String pokeBioma) {
        try {
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
            throw new NotFoundException("Pokémon de Id " + pokeId + "não encontrado");

        } catch(Exception e) {
            throw new InternalErrorException("Erro ao editar Pokémon" + e.getMessage());
        }

    }
}
