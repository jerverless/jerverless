/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.middleware.middleware;

import com.sun.net.httpserver.HttpExchange;
import org.jerverless.core.middleware.FunctionMiddleware;

/**
 *
 * @author shalithasuranga
 * @author Kasun Vithanage
 */
public class ContentTypeMiddleware extends FunctionMiddleware {
    private String contentType;

    public ContentTypeMiddleware(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public HttpExchange resolve(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().set("Content-Type", contentType);
        return httpExchange;
    }
}
