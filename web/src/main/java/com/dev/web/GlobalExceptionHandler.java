package com.dev.web;

import com.dev.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Moskalik
 *         created on 07.09.2016
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  Handles DTO Validation Errors.
     *
     * @param ex BindException
     * @return List<ErrorDTO>
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorDTO> validationExceptionHandler(BindException ex){
        return ex.getFieldErrors().stream()
                .map(error -> new ErrorDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
