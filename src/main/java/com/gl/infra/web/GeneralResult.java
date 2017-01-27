package com.gl.infra.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 * @author gantrylau
 * @since 2016年04月24日
 */
public class GeneralResult implements Serializable {

    private static final long serialVersionUID = -7605177651903670948L;

    private Boolean success;

    private String errorMsg;

    private Object data;

    public static GeneralResult success(Object object) {
        GeneralResult result = new GeneralResult();
        if (object instanceof Page) {
            Page page = (Page) object;
            Map<String, Object> data = new HashMap<>();
            data.put("content", page.getContent());
            data.put("size", page.getSize());
            data.put("page", page.getNumber() + 1);
            data.put("totalPages", page.getTotalPages());
            data.put("totalElements", page.getTotalElements());
            result.setData(data);
        } else {
            result.setData(object);
        }
        result.setSuccess(true);
        return result;
    }

    public static GeneralResult success() {
        return GeneralResult.success(null);
    }

    public static GeneralResult error(String msg) {
        GeneralResult result = new GeneralResult();
        result.setErrorMsg(msg);
        result.setSuccess(false);
        return result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
