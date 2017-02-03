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
@Table(name = "sys_menu")
public class SysMenu extends IdEntity<Long> {

    private String name;

    private String alias;

    private Integer sort;

    private String parent;

    private boolean leaf;

    @Column(length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Column(name = "is_leaf", columnDefinition = "bit default 0", nullable = false)
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
