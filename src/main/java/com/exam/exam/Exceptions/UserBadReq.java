package com.exam.exam.Exceptions;

public class UserBadReq extends RuntimeException {
    public UserBadReq(String message){
        super(message);
    }
}
