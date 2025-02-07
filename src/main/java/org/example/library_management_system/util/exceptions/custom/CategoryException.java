package org.example.library_management_system.util.exceptions.custom;

import org.example.library_management_system.util.exceptions.ServiceExeption;

public class CategoryException extends ServiceExeption {
    public CategoryException() {
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
