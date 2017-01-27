package com.gl.infra.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * servlet 3.0后, web.xml是可选的, 可以使用spring mvc 提供的WebApplicationInitializer去掉web.xml, 程序会自动载入这个类
 *
 * @author gantrylau
 * @since 2016年09月13日
 */
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("contextConfigLocation", "classpath:spring/spring-context.xml");
        servletContext.setInitParameter("logbackConfigLocation", "classpath:logback.xml");

        servletContext.addListener(new ContextLoaderListener());
        servletContext.addListener(new RequestContextListener());

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter());
        encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");


        servletContext.getServletRegistration("default").addMapping("/assets/*", "/configs/*", "/modules/*", "/favicon.ico", "/index.html");

        XmlWebApplicationContext webApplicationContext = new XmlWebApplicationContext();
        webApplicationContext.setConfigLocation("classpath:spring/spring-mvc.xml");
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("spring", new DispatcherServlet(webApplicationContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/api/*");


        ServletRegistration.Dynamic singlePageServlet = servletContext.addServlet("singlePage", new SinglePageServlet());
        singlePageServlet.addMapping("/");
    }
}
