package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Ataque;
import com.example.pokedex.Interface.AtaqueRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AtaqueService {
    
    private AtaqueRepository ataqueRepository;

    @Autowired
    public AtaqueService(AtaqueRepository ataqueRepository) {
        this.ataqueRepository = ataqueRepository;
    }

    public Ataque registrarAtaque(String ataqNome, String ataqTipo, int ataqDano, int ataqPrecisao, int pp) {
        Ataque novoAtaque = new Ataque();
        novoAtaque.setAtaqNome(ataqNome);
        novoAtaque.setAtaqTipo(ataqTipo);
        novoAtaque.setDano(ataqDano);
        novoAtaque.setPrecisao(ataqPrecisao);
        novoAtaque.setPp(pp);
        Ataque ataqSalvo = ataqueRepository.save(novoAtaque);
        return ataqSalvo;
    }

    public Ataque editarAtaque(@PathVariable Long ataqId, String ataqNome, String ataqTipo, int ataqDano, int ataqPrecisao, int pp) {
        Optional<Ataque> ataqExistente = ataqueRepository.findById(ataqId);

        if(ataqExistente.isPresent()) {
            Ataque ataqParaEditar = ataqExistente.get();
            ataqParaEditar.setAtaqNome(ataqNome);
            ataqParaEditar.setAtaqTipo(ataqTipo);
            ataqParaEditar.setDano(ataqDano);
            ataqParaEditar.setPrecisao(ataqPrecisao);
            ataqParaEditar.setPp(pp);

            return ataqueRepository.save(ataqParaEditar);
        }

        throw new EntityNotFoundException("Ataque não encontrado" + ataqId);
    }

    public List<Ataque> listarAtaques() {
        List<Ataque> ataques = ataqueRepository.findAll();
        return ataques;
    }
}
