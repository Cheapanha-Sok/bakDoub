package com.example.demo.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super();
    }
    public NotFoundException(Throwable cause){
        super(cause);
    }
    public NotFoundException(String email){
        super(email);
    }
    public NotFoundException(String email ,Throwable cause){
        super(email , cause);
    }


}
