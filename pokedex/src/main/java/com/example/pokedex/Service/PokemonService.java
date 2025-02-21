package com.example.pokedex.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Ataque;
import com.example.pokedex.Entity.Pokemon;
import com.example.pokedex.Entity.Regiao;
import com.example.pokedex.Entity.Treinador;
import com.example.pokedex.Interface.AtaqueRepository;
import com.example.pokedex.Interface.PokemonRepository;
import com.example.pokedex.Interface.RegiaoRepository;
import com.example.pokedex.Interface.TreinadorRepository;
import com.example.pokedex.err.BadRequestException;
import com.example.pokedex.err.InternalErrorException;
import com.example.pokedex.err.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class PokemonService {
    
    private PokemonRepository pokemonRepository;

    private AtaqueRepository ataqueRepository;

    private TreinadorRepository treinadorRepository;

    private RegiaoRepository regiaoRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, AtaqueRepository ataqueRepository, TreinadorRepository treinadorRepository, RegiaoRepository regiaoRepository) {
        this.pokemonRepository = pokemonRepository;
        this.ataqueRepository = ataqueRepository;
        this.treinadorRepository = treinadorRepository;
        this.regiaoRepository = regiaoRepository;
    }

    @Transactional
    public Pokemon registrarPokemon(String pokeNome, String pokeTipo, double pokeTamanho, double pokePeso, String pokeBioma, 
    List<Ataque> ataques, List<Treinador> treinadores, List<Regiao> regioes) {
        try {
            Pokemon novoPokemon = new Pokemon();
            novoPokemon.setPokeNome(pokeNome);
            novoPokemon.setPokeTipo(pokeTipo);
            novoPokemon.setPokeTamanho(pokeTamanho);
            novoPokemon.setPokePeso(pokePeso);
            novoPokemon.setPokeBioma(pokeBioma);

            // Buscar os objetos Ataque do banco de dados pelos IDs fornecidos
            List<Ataque> ataquesEncontrados = (ataques != null) ? ataques.stream()
                .map(ataque -> ataqueRepository.findById(ataque.getAtaqId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()): List.of();

            // Buscar os objetos Treinador do banco de dados pelos IDs fornecidos
            List<Treinador> treinadoresEncontrados = (treinadores != null) ? treinadores.stream()
                .map(treinador -> treinadorRepository.findById(treinador.getTreinaId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()) : List.of();

            // Buscar os objetos Regiao do banco de dados pelos IDs fornecidos
            List<Regiao> regioesEncontradas = (regioes != null) ? regioes.stream()
                .map(regiao -> regiaoRepository.findById(regiao.getRegId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()) : List.of();


            novoPokemon.setAtaques(ataquesEncontrados);
            novoPokemon.setTreinadores(treinadoresEncontrados);
            novoPokemon.setRegioes(regioesEncontradas);
            Pokemon pokemonSalvo = pokemonRepository.save(novoPokemon);
            System.out.println("Pokemon a ser salvo " + novoPokemon);
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
