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

import com.example.pokedex.Entity.Treinador;
import com.example.pokedex.Service.TreinadorService;

@RestController
@RequestMapping("/treinadores")
@CrossOrigin(origins = "*")
public class TreinadorController {
    
    private TreinadorService treinadorService;

    public TreinadorController(TreinadorService treinadorService) {
        this.treinadorService = treinadorService;
    }

    @PostMapping("registrar")
    public ResponseEntity<Treinador> registrarTreinador(@RequestBody Treinador dados) {
        Treinador treinadorSalvo = treinadorService.registrarTreinador(dados.getTreinaNome(), dados.getCidade());
        return ResponseEntity.ok(treinadorSalvo);
    }

    @GetMapping("listar")
    public ResponseEntity<List<Treinador>> listarTreinadores() {
        List<Treinador> treinadores = treinadorService.listarTreinadores();
        return ResponseEntity.ok(treinadores);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Treinador> editarTreinador(@PathVariable("id") Long treinaId, @RequestBody Treinador dados) {
        Treinador treinadorEditado = treinadorService.editarTreinador(treinaId, dados.getTreinaNome(), dados.getCidade());
        return ResponseEntity.ok(treinadorEditado);
    }   
}
