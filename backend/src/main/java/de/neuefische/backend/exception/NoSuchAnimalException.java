package de.neuefische.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Animal not found!")
public class NoSuchAnimalException extends RuntimeException{

public NoSuchAnimalException(){

}
    public NoSuchAnimalException(String s, Throwable cause){
        super(s, cause);
    }

    public NoSuchAnimalException( Throwable cause){
        super(cause);
    }

    public NoSuchAnimalException(String s){
        super(s);
    }

}
