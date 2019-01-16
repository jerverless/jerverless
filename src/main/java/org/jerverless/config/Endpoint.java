package org.jerverless.config;

public class Endpoint {
    private String route;
    private String command;

    private String method;
    private String contentType;
    private String cors;
    private String arguments;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCors() {
        return cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "route='" + route + '\'' +
                ", method='" + method + '\'' +
                ", contentType='" + contentType + '\'' +
                ", cors='" + cors + '\'' +
                ", command='" + command + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }
}
