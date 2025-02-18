package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Treinador;
import com.example.pokedex.Interface.TreinadorRepository;
import com.example.pokedex.err.BadRequestException;
import com.example.pokedex.err.InternalErrorException;
import com.example.pokedex.err.NotFoundException;

@Service
public class TreinadorService {
    
    private TreinadorRepository treinadorRepository;

    @Autowired
    public TreinadorService(TreinadorRepository treinadorRepository) {
        this.treinadorRepository = treinadorRepository;
    }

    public Treinador registrarTreinador(String treinaNome, String cidade) {
        try{

            Treinador novoTreinador = new Treinador();
            novoTreinador.setTreinaNome(treinaNome);
            novoTreinador.setCidade(cidade);
            Treinador treinadorSalvo = treinadorRepository.save(novoTreinador);
            return treinadorSalvo;
        } catch (Exception e) {
            throw new BadRequestException("Erro na requisição, o Treinador precisa ter um nome" + e.getMessage());
        }
    }

    public List<Treinador> listarTreinadores() {
        try {
            return treinadorRepository.findAll();

        } catch(Exception e) {
            throw new InternalErrorException("Erro ao listar Pokémons " + e.getMessage());
        }
    }

    public Treinador editarTreinador(@PathVariable Long treinaId, String treinaNome, String cidade) {
        try {

            Optional<Treinador> treinadorExistente = treinadorRepository.findById(treinaId);
            
            if(treinadorExistente.isPresent()) {
                Treinador treinadorParaEditar = treinadorExistente.get();
                treinadorParaEditar.setTreinaNome(treinaNome);
                treinadorParaEditar.setCidade(cidade);
                return treinadorRepository.save(treinadorParaEditar);
            }   
            throw new NotFoundException("Treinador não encontrado" + treinaId);

        } catch(Exception e) {
            throw new InternalErrorException("Erro ao editar treinador de Id " + treinaId + e.getMessage());
        }
    }
}
