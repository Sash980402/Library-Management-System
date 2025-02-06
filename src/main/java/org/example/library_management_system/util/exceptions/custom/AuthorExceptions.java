package org.example.library_management_system.util.exceptions.custom;

import org.example.library_management_system.util.exceptions.ServiceExeption;

public class AuthorExceptions extends ServiceExeption {
   public AuthorExceptions() {

   }
   public AuthorExceptions(String message) {
       super(message);
   }
   public AuthorExceptions(String message, Throwable cause) {
       super(message, cause);
   }


}
