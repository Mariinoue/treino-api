package com.example.treino_api.exception;

public class TreinoNotFoundException extends RuntimeException {
    public TreinoNotFoundException(String message) {
        super(message);
    }
}