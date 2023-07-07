package com.egor.spring.mvc_hibernate_aop.controller;

import com.egor.spring.mvc_hibernate_aop.exceptionHandling.AlreadyAuthorizedException;
import com.egor.spring.mvc_hibernate_aop.exceptionHandling.IncorrectData;

import com.egor.spring.mvc_hibernate_aop.exceptionHandling.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {


//Метод ответственный за обработку исключений
@ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchEntityException exception){
    System.out.println("triggered NoSuchEntityException");
        IncorrectData data=new IncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception){
        System.err.println("triggered Exception");
        IncorrectData data=new IncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(IndexOutOfBoundsException exception){
        System.err.println("triggered IndexOutOfBoundsException");
        IncorrectData data=new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(AlreadyAuthorizedException exception){
        System.err.println("triggered AlreadyAuthorizedException");
        IncorrectData data=new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
