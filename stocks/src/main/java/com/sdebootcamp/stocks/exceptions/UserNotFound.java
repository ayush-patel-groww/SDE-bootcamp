package com.sdebootcamp.stocks.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(String message){
      super(message);
    }
}
