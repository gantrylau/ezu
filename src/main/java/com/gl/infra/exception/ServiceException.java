package com.gl.infra.exception;

/**
 * @author gantrylau
 * @since 2017/2/16
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
}
