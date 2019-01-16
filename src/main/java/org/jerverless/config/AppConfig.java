package org.jerverless.config;

import java.util.List;

public class AppConfig {
    private int port = 8080;
    private List<Endpoint> endpoints;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", endpoints=" + endpoints +
                '}';
    }
}
