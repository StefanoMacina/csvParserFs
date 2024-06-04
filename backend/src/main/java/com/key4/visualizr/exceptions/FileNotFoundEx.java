package com.key4.visualizr.exceptions;

public class FileNotFoundEx extends RuntimeException{

    public FileNotFoundEx(String message, Throwable err) {
        super(message, err);
    }
}
