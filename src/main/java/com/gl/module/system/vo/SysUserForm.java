package com.gl.module.system.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author gantrylau
 * @since 2017/2/3
 */
public class SysUserForm {

    @NotBlank(message = "用户名不可为空")
    private String username;

    @Email
    private String email;

    private String telephone;

    public static class SysUserFormValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return SysUserForm.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            SysUserForm form = (SysUserForm) target;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
