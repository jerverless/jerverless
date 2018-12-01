/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.middleware.middleware;

import com.sun.net.httpserver.HttpExchange;
import org.jerverless.boot.config.TypeConfig;
import org.jerverless.core.middleware.FunctionMiddleware;
import org.jerverless.core.middleware.IFunctionMiddleware;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class ContentTypeMiddleware extends FunctionMiddleware {
    
    private String contentTypeHolder;

    public ContentTypeMiddleware(TypeConfig typeConfig) {
        contentTypeHolder = typeConfig.getContentType();
    }
    

    @Override
    public HttpExchange resolve(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().set("Content-Type", contentTypeHolder);
        return httpExchange;
    }
    
}
