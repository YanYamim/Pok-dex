package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokedex.Entity.Treinador;
import com.example.pokedex.Interface.TreinadorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TreinadorService {
    
    private TreinadorRepository treinadorRepository;

    @Autowired
    public TreinadorService(TreinadorRepository treinadorRepository) {
        this.treinadorRepository = treinadorRepository;
    }

    public Treinador registrarTreinador(String treinaNome, String cidade) {
        Treinador novoTreinador = new Treinador();
        novoTreinador.setTreinaNome(treinaNome);
        novoTreinador.setCidade(cidade);
        Treinador treinadorSalvo = treinadorRepository.save(novoTreinador);
        return treinadorSalvo;
    }

    public List<Treinador> listarTreinadores() {
        List<Treinador> treinadores = treinadorRepository.findAll();
        return treinadores;
    }

    public Treinador editarTreinador(Long treinaId, String treinaNome, String cidade) {
        Optional<Treinador> treinadorExistente = treinadorRepository.findById(treinaId);
        
        if(treinadorExistente.isPresent()) {
            Treinador treinadorParaEditar = treinadorExistente.get();
            treinadorParaEditar.setTreinaNome(treinaNome);
            treinadorParaEditar.setCidade(cidade);
            return treinadorRepository.save(treinadorParaEditar);
        }

        throw new EntityNotFoundException("Treinador não encontrado" + treinaId);
    }
}
