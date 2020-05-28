package com.gsir.monitor.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class YamlUtils {

    public static Object loadFromFile(String path) throws FileNotFoundException {
        InputStream input = new FileInputStream(new File(path));
        Yaml yaml = new Yaml();
        Object data = yaml.load(input);

        return data;
    }
}
