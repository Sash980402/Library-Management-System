package org.example.library_management_system.util.exceptions.custom;

import org.example.library_management_system.util.exceptions.ServiceExeption;

public class MemberException extends ServiceExeption {
    public MemberException() {
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
