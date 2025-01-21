package org.example.library_management_system.util.exceptions;

public class MemberException extends Exception {
    public MemberException() {
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
