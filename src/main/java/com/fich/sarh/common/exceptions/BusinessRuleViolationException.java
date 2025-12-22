package com.fich.sarh.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessRuleViolationException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;


    public BusinessRuleViolationException(String resourceName, String fieldName, Object fieldValue)
    {
        super(String.format("%s no fue encontrado con: %s= '%s'", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public BusinessRuleViolationException(String resourceName){
        super(String.format("%s en el sistema.", resourceName ));
        this.resourceName = resourceName;
    }


}
