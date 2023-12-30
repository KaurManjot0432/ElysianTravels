package com.manjot.ElysianTravels.exception;

public class ElysianTravelsException extends RuntimeException {

    public ElysianTravelsException(String message) {
        super(message);
    }

    public ElysianTravelsException(String message, Throwable cause) {
        super(message, cause);
    }
}
