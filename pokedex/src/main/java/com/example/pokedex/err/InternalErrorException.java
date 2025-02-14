package com.example.pokedex.err;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String mensagem) {
        super(mensagem);
    }
}

