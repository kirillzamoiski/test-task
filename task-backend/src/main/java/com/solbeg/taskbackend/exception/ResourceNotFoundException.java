package com.solbeg.taskbackend.exception;

public class ResourceNotFoundException extends BaseException {

    private static final String RECOURSE_NOT_FOUND = "Resource not found";
    private static final String RECOURSE_BY_UUID_NOT_FOUND = "Resource by ID = '%s' not found";
    private static final String RECOURSE_BY_FIELD_NOT_FOUND = "Resource by %s = '%s' not found";

    public ResourceNotFoundException() {
        super(RECOURSE_NOT_FOUND);
    }

    public ResourceNotFoundException(String uuid) {
        super(String.format(RECOURSE_BY_UUID_NOT_FOUND, uuid));
    }

    public ResourceNotFoundException(String fieldName, String value) {
        super(String.format(RECOURSE_BY_FIELD_NOT_FOUND, fieldName, value));
    }
}
