package com.mise.hotspot.incantation.exception;

public interface ErrorType {

    int getCode();
    String getMessage();
    int getHttpCode();
    
}
