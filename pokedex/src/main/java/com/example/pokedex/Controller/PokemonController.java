package com.example.pokedex.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pokedex.Entity.Pokemon;
import com.example.pokedex.Service.PokemonService;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "*")
public class PokemonController {
    
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("registrar")
    public ResponseEntity<Pokemon> registrarPokemon(@RequestBody Pokemon dados) {
        Pokemon novoPokemon = pokemonService.registrarPokemon(dados.getPokeNome(), dados.getPokeTipo(), dados.getPokeTamanho(), dados.getPokePeso(), dados.getPokeBioma());
        return ResponseEntity.ok(novoPokemon);
    }

    @GetMapping("listar")
    public ResponseEntity<List<Pokemon>> listarPokemons() {
        List<Pokemon> pokemons = pokemonService.listarPokemons();
        return ResponseEntity.ok(pokemons);
    }

    @PutMapping("editar")
    public ResponseEntity<Pokemon> editarPokemon(@PathVariable("id") Long pokeId, @RequestBody Pokemon pokemon) {
        Pokemon pokemonEditado = pokemonService.editarPokemon(pokeId, pokemon.getPokeNome(), pokemon.getPokeTipo(), pokemon.getPokeTamanho(), pokemon.getPokePeso(), pokemon.getPokeBioma());
        return ResponseEntity.ok(pokemonEditado);
    }
}
