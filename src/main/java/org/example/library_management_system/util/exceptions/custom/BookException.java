package org.example.library_management_system.util.exceptions.custom;

import org.example.library_management_system.util.exceptions.ServiceExeption;

public class BookException extends ServiceExeption {
    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }
}
