package com.gl.module.system.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
public class SysMenuVO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String alias;

    private Integer sort;

    private String parentAlias;

    private boolean leaf;

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

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
