package com.gl.module.system.vo;

/**
 * @author gantrylau
 * @since 2017/2/14
 */
public class SysUserQueryParam {
    public enum Type {
        USERNAME,
        TELEPHONE,
        EMAIL
    }

    private Type type;

    private String q;

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
