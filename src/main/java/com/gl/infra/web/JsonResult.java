package com.gl.infra.web;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gantrylau
 * @since 2016年04月24日
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -7605177651903670948L;

    private Boolean success;

    private String errorCode;

    private String errorMsg;

    private Object data;

    public static JsonResult build(Object object, BindingResult bindingResult) {
        JsonResult result = new JsonResult();
        if (bindingResult != null) {
            if (bindingResult.getFieldErrorCount() > 0) {
                result.setSuccess(false);
                FieldError fieldError = bindingResult.getFieldError();
                result.setErrorMsg(fieldError.getDefaultMessage());
                return result;
            }
        }
        result.setSuccess(true);
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
        return result;
    }

    public static JsonResult build(BindingResult result) {
        return build(null, result);
    }

    public static JsonResult success(Object object) {
        return build(object, null);
    }

    public static JsonResult success() {
        return JsonResult.success(null);
    }

    public static JsonResult error(String msg) {
        JsonResult result = new JsonResult();
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
