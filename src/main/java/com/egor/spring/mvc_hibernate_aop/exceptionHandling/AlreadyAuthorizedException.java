package com.egor.spring.mvc_hibernate_aop.exceptionHandling;

public class AlreadyAuthorizedException extends RuntimeException{
    public AlreadyAuthorizedException(String message) {
        super(message);
    }
}
