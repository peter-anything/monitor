package com.gsir.monitor.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String beanToJson(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        objectMapper.writeValue(gen, obj);
        gen.close();

        return sw.toString();
    }
    
    public static <T> T jsonToBean(String jsonStr, Class<T> objClass) throws JsonParseException, 
        JsonMappingException, IOException {
        return objectMapper.readValue(jsonStr, objClass);
    } 
}
