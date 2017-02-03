package com.gl.infra.web;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gantrylau
 * @since 2017/2/3
 */
public class ValidationRuleResult {

    private static Map<Class<?>, String> ruleMap = new HashMap<>();

    static {
        ruleMap.put(NotBlank.class, "required");
        ruleMap.put(NotEmpty.class, "required");
    }

    public static <T> HashMap<String, Object> build(Class<T> formClazz) {
        System.out.println(formClazz);
        return null;
    }
}
