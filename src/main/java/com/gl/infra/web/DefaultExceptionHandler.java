package com.gl.infra.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gantrylau
 * @since 2017/2/5
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    JsonResult handleException(HttpServletRequest request, Exception ex) {
        log.error("", ex);
        return JsonResult.error(ex.getMessage());
    }
}
