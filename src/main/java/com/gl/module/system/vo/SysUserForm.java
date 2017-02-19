package com.gl.module.system.vo;

import com.gl.module.system.domain.SysUser.Sex;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author gantrylau
 * @since 2017/2/3
 */
public class SysUserForm {

    private Long id;

    @NotBlank(message = "用户名不可为空")
    @Length(min = 5, max = 20, message = "用户名长度为5-20个字符")
    private String username;

    private String email;

    private String telephone;

    private Sex sex;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
