package com.gl.infra.convert;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.HashSet;

/**
 * @author gantrylau
 * @since 2016年04月23日
 */
public class LongHashSetToStringConvert implements AttributeConverter<HashSet<Long>, String> {

    private final static String separator = ",";

    @Override
    public String convertToDatabaseColumn(HashSet<Long> longs) {
        if (longs == null || longs.size() < 1)
            return null;
        return StringUtils.join(longs, separator);
    }

    @Override
    public HashSet<Long> convertToEntityAttribute(String s) {
        if (s == null || "".equals(s))
            return new HashSet<>();
        String[] strArr = s.split(separator);
        HashSet<Long> set = new HashSet<>();
        for (String str : strArr) {
            if (!"".equals(str))
                set.add(Long.valueOf(str));
        }
        return set;
    }
}
