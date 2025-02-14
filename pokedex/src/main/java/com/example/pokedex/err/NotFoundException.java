package com.example.pokedex.err;

public class NotFoundException extends RuntimeException {
    
   public NotFoundException(String mensagem) {
    super(mensagem);
   }
}
