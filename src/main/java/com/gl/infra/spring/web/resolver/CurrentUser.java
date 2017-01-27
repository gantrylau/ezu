package com.gl.infra.spring.web.resolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>绑定当前登录的用户</p>
 *
 * @author gantrylau
 * @since 2015年09月13日
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    String value() default "CURRENT_USER";

}