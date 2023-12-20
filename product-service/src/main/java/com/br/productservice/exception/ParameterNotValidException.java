package com.br.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ParameterNotValidException extends RuntimeException{

    public ParameterNotValidException(String fieldName, String fieldValue){
        super(String.format("%s can not be '%s'", fieldName, fieldValue));
    }
}