package org.jerverless.config.app;

/**
 * @author Kasun Vithanage
 */
public class Route {
    private String endpoint;
    private String command;

    // TODO need validators
    private String method = "POST";
    private String contentType = "text/html";
    private String cors;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getCommandToArray() {
        return command.split(" ");
    }

    public String getCors() {
        return cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }

    @Override
    public String toString() {
        return "Route{" +
                "endpoint='" + endpoint + '\'' +
                ", command='" + command + '\'' +
                ", method='" + method + '\'' +
                ", contentType='" + contentType + '\'' +
                ", cors='" + cors + '\'' +
                '}';
    }
}
