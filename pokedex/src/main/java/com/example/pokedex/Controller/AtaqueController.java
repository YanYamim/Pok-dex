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

import com.example.pokedex.Entity.Ataque;
import com.example.pokedex.Service.AtaqueService;

@RestController
@RequestMapping("/ataques")
@CrossOrigin(origins = "*")
public class AtaqueController {
    
    private AtaqueService ataqueService;

    public AtaqueController(AtaqueService ataqueService) {
        this.ataqueService = ataqueService;
    }

    @PostMapping("registrar")
    public ResponseEntity<Ataque> registrarAtaque(@RequestBody Ataque dados) {
        Ataque ataqueSalvo = ataqueService.registrarAtaque(dados.getAtaqNome(), dados.getAtaqTipo(), dados.getDano(), dados.getPrecisao(), dados.getPp());
        return ResponseEntity.ok(ataqueSalvo);
    }

    @GetMapping("listar")
    public ResponseEntity<List<Ataque>> listarAtaques() {
        List<Ataque> ataques = ataqueService.listarAtaques();
        return ResponseEntity.ok(ataques);
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<Ataque> listarAtaquePorId(@PathVariable("id") Long ataqId) {
        Ataque ataquePotId = ataqueService.listarAtaquePorId(ataqId);
        return ResponseEntity.ok(ataquePotId);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Ataque> editarAtaque(@PathVariable("id") Long ataqId, @RequestBody Ataque ataque) {
        Ataque ataqueEditado = ataqueService.editarAtaque(ataqId, ataque.getAtaqNome(), ataque.getAtaqTipo(), ataque.getDano(), ataque.getPrecisao(), ataque.getPp());
        return ResponseEntity.ok(ataqueEditado);
    }
}
