package com.profi.promoservice.exception.handler;

import com.profi.promoservice.exception.custom.RaffleException;
import com.profi.promoservice.exception.responce_message.DefaultResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PromotionExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DefaultResponseMessage> noSuchElementExceptionHandler(NoSuchElementException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(ZonedDateTime.now().toString(),
                "No such element", request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultResponseMessage> raffleException(RaffleException e, HttpServletRequest request){
        return new ResponseEntity<>(new DefaultResponseMessage(ZonedDateTime.now().toString(),
                e.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT);
    }
}
