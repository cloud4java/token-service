package com.silvasoft.tokenservice.presentation;

import com.silvasoft.tokenservice.domain.TokenValidationException;
import com.silvasoft.tokenservice.presentation.dto.TokenErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class TokenErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        TokenErrorResponse error = new TokenErrorResponse();
        error.setMessage("This resource was not found in our records" + ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleBackRequest(NoHandlerFoundException ex) {
        TokenErrorResponse error = new TokenErrorResponse();
        error.setMessage("Invalid input provided" + ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<TokenErrorResponse> validationError(MethodArgumentNotValidException ex) {
        return getTokenErrorResponseResponseEntity(ex);
    }

    @ExceptionHandler(value = {TokenValidationException.class})
    public ResponseEntity<TokenErrorResponse> invalidToken(MethodArgumentNotValidException ex) {
        return getTokenErrorResponseResponseEntity(ex);
    }

    private ResponseEntity<TokenErrorResponse> getTokenErrorResponseResponseEntity(final MethodArgumentNotValidException ex) {
        TokenErrorResponse res = new TokenErrorResponse();
        res.setMessage(ObjectUtils.isEmpty(ex.getMessage()) ? "" : ex.getMessage().substring(ex.getMessage().lastIndexOf(";")));
        res.setMessage(ex.getBindingResult().getFieldError("value").getDefaultMessage());
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
