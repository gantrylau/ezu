package com.gl.infra.web;

import com.gl.module.system.vo.SysRoleForm;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gantrylau
 * @since 2017/2/3
 */
public class ValidationRuleResult {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static final String REQUIRED_RULE_NAME = "required";
    private static final String MESSAGE_RULE_NAME = "message";

    private static Map<String, String> ruleMap = new HashMap<>();

    static {
        ruleMap.put(NotBlank.class.getSimpleName(), REQUIRED_RULE_NAME);
        ruleMap.put(NotEmpty.class.getSimpleName(), REQUIRED_RULE_NAME);
    }

    public static <T> Map<String, Object> build(Class<T> formClazz) {
        BeanDescriptor descriptor = validator.getConstraintsForClass(formClazz);
        Set<PropertyDescriptor> props = descriptor.getConstrainedProperties();

        Map<String, Object> result = new HashMap<>();

        for (PropertyDescriptor prop : props) {
            List<Map<String, Object>> ruleList = new ArrayList<>();
            Set<ConstraintDescriptor<?>> constraints = prop.getConstraintDescriptors();

            for (ConstraintDescriptor<?> c : constraints) {
                Map<String, Object> attributes = new HashMap<>();
                Class<?> type = c.getAnnotation().annotationType();
                String constraintName = type.getSimpleName();
                String mappedName = ruleMap.get(constraintName);
                if (mappedName != null) {
                    if (REQUIRED_RULE_NAME.equals(mappedName)) {
                        attributes.put(REQUIRED_RULE_NAME, true);
                    }
                }
                for (String key : c.getAttributes().keySet()) {
                    if (key.equals("payload") || key.equals("groups")) {
                        continue;
                    }
                    attributes.put(key, c.getAttributes().get(key));
                }
                ruleList.add(attributes);
            }
            result.put(prop.getPropertyName(), ruleList);
        }
        return result;
    }

    private static List<Field> getFields(Class formClazz) {
        List<Field> fields = new ArrayList<>();
        Class clazz = formClazz;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fs = clazz.getDeclaredFields();
            if (fs != null) {
                fields.addAll(Arrays.asList(fs));
            }
        }
        return fields;
    }

    public static void main(String[] args) {
        ValidationRuleResult.build(SysRoleForm.class);
    }
}
