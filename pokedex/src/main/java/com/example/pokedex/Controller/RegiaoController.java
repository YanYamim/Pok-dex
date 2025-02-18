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

import com.example.pokedex.Entity.Regiao;
import com.example.pokedex.Service.RegiaoService;

@RestController
@RequestMapping("/regioes")
@CrossOrigin(origins = "*")
public class RegiaoController {
    
    private RegiaoService regiaoService;

    public RegiaoController(RegiaoService regiaoService) {
        this.regiaoService = regiaoService;
    }

    @PostMapping("registrar")
    public ResponseEntity<Regiao> registrarRegiao(@RequestBody Regiao dados) {
        Regiao regiaoSalva = regiaoService.registrarRegiao(dados.getRegNome());
        return ResponseEntity.ok(regiaoSalva);
    }

    @GetMapping("listar")
    public ResponseEntity<List<Regiao>> listarRegioes() {
        List<Regiao> regioes = regiaoService.listarRegioes();
        return ResponseEntity.ok(regioes);
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<Regiao> listarRegiaoPorId(@PathVariable("id") Long regId) {
        Regiao regiaoPorId = regiaoService.listarRegiaoPorId(regId);
        return ResponseEntity.ok(regiaoPorId);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Regiao> editarRegiao(@PathVariable("id") Long regId, @RequestBody Regiao regiao) {
        Regiao regiaoEditada = regiaoService.editarRegiao(regId, regiao.getRegNome());
        return ResponseEntity.ok(regiaoEditada);
    }
}
