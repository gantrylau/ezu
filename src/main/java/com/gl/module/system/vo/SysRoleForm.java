package com.gl.module.system.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author gantrylau
 * @since 2017/2/16
 */
public class SysRoleForm {

    private Long id;

    @NotBlank(message = "角色名不可为空")
    @Length(min = 1, max = 50, message = "角色名长度为1-50个字符")
    private String name;

    @NotBlank(message = "角色别名不可为空")
    private String alias;

    @Max(value = 99999, message = "角色权限值的范围为0~99999")
    @Min(value = 0, message =  "角色权限值的范围为0~99999")
    private int power;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
