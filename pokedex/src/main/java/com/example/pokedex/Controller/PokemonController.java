package com.example.pokedex.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "*")
public class PokemonController {
    
}
