package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Regiao;
import com.example.pokedex.Interface.RegiaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegiaoService {
    
    private RegiaoRepository regiaoRepository;

    @Autowired
    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    public Regiao registrarRegiao(String regNome) {
        Regiao novaRegiao = new Regiao();
        novaRegiao.setRegNome(regNome);
        Regiao regSalva = regiaoRepository.save(novaRegiao);
        return regSalva;
    }

    public Regiao editarRegiao(@PathVariable Long regId, String regNome) {
        Optional<Regiao> regExistente = regiaoRepository.findById(regId);

        if (regExistente.isPresent()) {
            Regiao regParaEditar = regExistente.get();
            regParaEditar.setRegNome(regNome);
            return regiaoRepository.save(regParaEditar);
        }

        throw new EntityNotFoundException("Região não encontrada" + regId);
    }

    public List<Regiao> listarRegioes() {
        List<Regiao> regioes = regiaoRepository.findAll();
        return regioes;
    }
}
