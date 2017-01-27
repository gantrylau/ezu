package com.gl.infra.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gantrylau
 * @since 2016年05月11日
 */
public class SinglePageServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SinglePageServlet.class);

    private static final long serialVersionUID = -1368293417177225152L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath() + (req.getPathInfo() == null ? "" : req.getPathInfo());
        log.info("拦截到请求{}, 转发到首页", url);
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}
