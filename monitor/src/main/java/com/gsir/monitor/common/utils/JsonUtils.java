package com.gsir.monitor.common.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String beanToJson(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        objectMapper.writeValue(gen, obj);
        gen.close();

        return sw.toString();
    }
    
    public static <T> T jsonToBean(String jsonStr, Class<T> objClass) throws JsonParseException, 
        JsonMappingException, IOException {
        return objectMapper.readValue(jsonStr, objClass);
    } 
}
