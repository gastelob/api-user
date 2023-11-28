package com.gastelob.apiuser.common;

public class StandardizedApiExceptionResponse {

    private String message;

    public StandardizedApiExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
