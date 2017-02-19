package com.gl.module.system.domain;

import com.gl.infra.spring.data.domain.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author gantrylau
 * @since 2017/1/27
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends IdEntity<Long> {

    private String name;

    private String alias;

    private int power;

    @Column(length = 50)
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

    @Column(length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
