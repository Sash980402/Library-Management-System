package org.example.library_management_system.util.exceptions.custom;

import org.example.library_management_system.util.exceptions.ServiceExeption;

import java.rmi.ServerException;

public class PublisherException extends ServiceExeption {
    public PublisherException()    {
    }

    public PublisherException(String message) {
        super(message);
    }

    public PublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
