package com.gl.module.system.vo;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
public class SysMenuQueryParam {

    public enum Type {
        /**
         * 父菜单名字
         */
        ALIAS,
        /**
         * 菜单名
         */
        NAME
    }

    private Long id;

    private Type type;

    private String q;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
