package dev.jgapi.jg_api.exceptions;

public class RestActionException extends Exception {
    public RestActionException(String message) {
        super("[ERROR] [RestActionException] " + message);
    }
}
