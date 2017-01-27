package com.gl.infra.spring.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 * @author gantrylau
 * @since 2016年05月28日
 */
public class DateConverter implements Converter<String, Date> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String source) {
        sdf.setLenient(false);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
