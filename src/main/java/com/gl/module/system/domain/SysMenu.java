package com.gl.module.system.domain;

import com.gl.infra.spring.data.domain.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu extends IdEntity<Long> {

    private String name;

    private String alias;

    private Integer sort;

    private String parentAlias;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentAlias() {
        return parentAlias;
    }

    public void setParentAlias(String parentAlias) {
        this.parentAlias = parentAlias;
    }
}
