package org.example.library_management_system.util.exceptions;

public class ServiceExeption extends Exception{
    public ServiceExeption() {
    }

    public ServiceExeption(String message) {
        super(message);
    }

    public ServiceExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
