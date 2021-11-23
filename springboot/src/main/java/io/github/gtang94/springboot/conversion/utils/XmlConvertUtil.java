package io.github.gtang94.springboot.conversion.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.gtang94.springboot.conversion.entity.User;

import java.io.File;
import java.io.IOException;

/**
 * @author tanggq
 * @class XmlConvertUtil
 * @description
 * @date 2021/6/3
 **/
public class XmlConvertUtil {

    public static XmlMapper xmlBuilder() {
        XmlMapper builder = XmlMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                .build();
        return builder;
//        return new XmlMapper();
    }

    public static Object xml2JavaBean(XmlMapper builder, File file) throws IOException {
        Object object = builder.readValue(file, User.class);
        return object;
    }

    public static String bean2Xml(XmlMapper builder, Object object) throws JsonProcessingException {
        return builder.writeValueAsString(object);
    }
}
