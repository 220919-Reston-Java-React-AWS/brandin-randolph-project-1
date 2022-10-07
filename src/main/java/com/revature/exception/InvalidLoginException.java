package com.revature.exception;

public class InvalidLoginException extends Exception {

    public InvalidLoginException(String message){ //constructor where we are calling the parent class constructor, and passing in the message.
        super(message);
    }


}
