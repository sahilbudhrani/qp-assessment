package com.qp.groceryappapis.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resource;
    String field;
    String value;

    public ResourceNotFoundException(String resource, String field, String value) {
        super(String.format("%s not found with %s : %s", resource, field, value));
        this.resource = resource;
        this.field = field;
        this.value = value;
    }

    public ResourceNotFoundException(String resource, String value) {
        super(String.format("%s ordered quantity not available : %s", resource, value));
        this.resource = resource;
        this.value = value;
    }
}
