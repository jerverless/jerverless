package org.jerverless.boot.config;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class RoutesConfig {
    private static RoutesConfig instance = null;
    private ArrayList<ConfigServerlessCommand> routes;

    private RoutesConfig(Properties config) {
        routes = new ArrayList<>();
        for(String key : config.stringPropertyNames()) {
            if(key.startsWith("/")) {
                routes.add(new ConfigServerlessCommand(key, config.getProperty(key)));
            }
        }
    }

    public static RoutesConfig create(Properties config) {
        if (instance == null)
            instance = new RoutesConfig(config);
        return instance;
    }

    public ArrayList<ConfigServerlessCommand> getRoutes() {
        return routes;
    }

    public  static  RoutesConfig getInstance() {
        return instance;
    }

}
