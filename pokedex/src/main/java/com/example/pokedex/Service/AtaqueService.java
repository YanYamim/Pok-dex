package com.example.pokedex.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pokedex.Entity.Ataque;
import com.example.pokedex.Interface.AtaqueRepository;
import com.example.pokedex.err.BadRequestException;
import com.example.pokedex.err.NotFoundException;

@Service
public class AtaqueService {
    
    private AtaqueRepository ataqueRepository;

    @Autowired
    public AtaqueService(AtaqueRepository ataqueRepository) {
        this.ataqueRepository = ataqueRepository;
    }

    public Ataque registrarAtaque(String ataqNome, String ataqTipo, int ataqDano, int ataqPrecisao, int pp) {
        try {

            Ataque novoAtaque = new Ataque();
            novoAtaque.setAtaqNome(ataqNome);
            novoAtaque.setAtaqTipo(ataqTipo);
            novoAtaque.setDano(ataqDano);
            novoAtaque.setPrecisao(ataqPrecisao);
            novoAtaque.setPp(pp);
            Ataque ataqSalvo = ataqueRepository.save(novoAtaque);
            return ataqSalvo;
        } catch(Exception e) {
            throw new BadRequestException("Erro na requisição de Ataque, o nome não pode ser nulo " + e.getMessage());
        }
    }

    public Ataque editarAtaque(@PathVariable Long ataqId, String ataqNome, String ataqTipo, int ataqDano, int ataqPrecisao, int pp) {
        
        try{
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
            throw new NotFoundException("Ataque não encontrado" + ataqId);
        } catch(Exception e) {
            throw new BadRequestException("Erro na requisição de editar Ataque de Id " + ataqId + e.getMessage());
        }
    }

    public List<Ataque> listarAtaques() {
        try {
            return ataqueRepository.findAll();
        } catch(Exception e) {
            throw new NotFoundException("Pokémons não encontrados");
        }
    }

    public Ataque listarAtaquePorId(Long ataqId) {
        Optional<Ataque> ataquePorId = ataqueRepository.findById(ataqId);

        if(ataquePorId.isPresent()) {
            return ataquePorId.get();
        }

        throw new NotFoundException("Ataque de Id " + ataqId + "não encontrado");
    }
}
