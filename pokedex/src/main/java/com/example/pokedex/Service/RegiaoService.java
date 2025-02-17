package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Regiao;
import com.example.pokedex.Interface.RegiaoRepository;
import com.example.pokedex.err.InternalErrorException;
import com.example.pokedex.err.NotFoundException;

@Service
public class RegiaoService {
    
    private RegiaoRepository regiaoRepository;

    @Autowired
    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    public Regiao registrarRegiao(String regNome) {
        try {

            Regiao novaRegiao = new Regiao();
            novaRegiao.setRegNome(regNome);
            Regiao regSalva = regiaoRepository.save(novaRegiao);
            return regSalva;
        } catch(Exception e) {
            throw new InternalErrorException("Erro ao registrar região " + e.getMessage());
        }
    }

    public Regiao editarRegiao(@PathVariable Long regId, String regNome) {
        try {

            Optional<Regiao> regExistente = regiaoRepository.findById(regId);
            
            if (regExistente.isPresent()) {
                Regiao regParaEditar = regExistente.get();
                regParaEditar.setRegNome(regNome);
                return regiaoRepository.save(regParaEditar);
            }
            throw new NotFoundException("Região não encontrada" + regId);

        } catch(Exception e) {
            throw new InternalErrorException("Erro ao editar região de Id" + regId + e.getMessage());
        }
    }

    public List<Regiao> listarRegioes() {
        try{
            return regiaoRepository.findAll();
        } catch(Exception e) {
            throw new InternalErrorException("Erro ao listar regiões");
        }
    }
}
