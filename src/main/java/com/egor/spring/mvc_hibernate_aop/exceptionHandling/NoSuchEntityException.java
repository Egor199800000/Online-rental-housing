package com.egor.spring.mvc_hibernate_aop.exceptionHandling;

public class NoSuchEntityException extends RuntimeException{


    public NoSuchEntityException(String message) {
        super(message);
    }

}
