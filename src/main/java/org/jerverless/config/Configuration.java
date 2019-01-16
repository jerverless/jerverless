package org.jerverless.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kasun Vithanage
 */
public class Configuration {
    String path = ".jerverless";
    AppConfig appConfig;

    public Configuration(String path) throws IOException {
        this.path = path;
        Yaml yaml = new Yaml(new Constructor(AppConfig.class));

        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);

        appConfig = (AppConfig) yaml.load(inputStream);
    }

    public String getPath() {
        return path;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "path='" + path + '\'' +
                ", appConfig=" + appConfig +
                '}';
    }
}
