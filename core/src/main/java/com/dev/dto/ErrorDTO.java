package com.dev.dto;

/**
 * @author Vitaly Moskalik
 *         created on 07.09.2016
 */
public class ErrorDTO {

    private String field;
    private String message;

    public ErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public ErrorDTO setField(String field) {
        this.field = field;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
