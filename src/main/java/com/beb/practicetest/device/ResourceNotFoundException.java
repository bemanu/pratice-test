package com.beb.practicetest.device;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public  ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final String message, final Throwable throwable) {
        super(message,throwable);
    }

}
