package org.jerverless.config.app;

import java.util.List;

/**
 * @author Kasun Vithanage
 */

public class AppConfig implements Configuration {
    private int port = 8080;
    private String cors;
    private List<Route> routes;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getCors() {
        return cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }

    public boolean isCorsEnabled() {
        return cors != null;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", cors='" + cors + '\'' +
                ", routes=" + routes +
                '}';
    }
}
