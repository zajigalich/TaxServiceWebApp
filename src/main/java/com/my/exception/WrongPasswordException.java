package com.my.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) { super(message);}

}
