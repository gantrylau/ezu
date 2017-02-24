package com.gl.infra.util;


import com.gl.infra.exception.ServiceException;

import java.util.regex.Pattern;

/**
 * 增强型校验器
 * @author gantrylau
 * @since 2017/2/22
 */
public class Conditions {

    private static final Pattern p = Pattern.compile("\\{}");

    public static void checkArgument(boolean expression, String message) {
        if (!expression)
            throw new ServiceException(message);
    }

    public static void checkArgument(boolean expression, String message, Object... args) {
        if (!expression) {
            if (message != null & args != null) {
                for (Object obj : args) {
                    message = message.replaceFirst("\\{}", obj.toString());
                }
                message = message.replaceAll("\\{}", "");
            }
            throw new ServiceException(message);
        }

    }

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null)
            throw new ServiceException(message);
        return reference;
    }
}
