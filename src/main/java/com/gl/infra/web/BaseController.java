package com.gl.infra.web;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @author gantrylau
 * @since 2016年08月29日
 */
public abstract class BaseController {

    @Autowired
    private HttpSession httpSession;

    protected UserPrincipal getUserPrincipal() {
        return (UserPrincipal)httpSession.getAttribute("CURRENT_USER");
    }

}
