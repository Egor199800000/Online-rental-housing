package com.egor.spring.mvc_hibernate_aop.exceptionHandling;

public class NoAuthorizedUserException extends RuntimeException{

    public NoAuthorizedUserException(String message) {
        super(message);
    }
}
