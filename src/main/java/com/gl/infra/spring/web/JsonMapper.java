package com.gl.infra.spring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;

/**
 * @author gantrylau
 * @since 2016年05月28日
 */
public class JsonMapper {

    private ObjectMapper objectMapper;


    public JsonMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:ss"));
    }

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
