// MIT License
//
// Copyright (c) 2018 Shalitha Suranga
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package org.jerverless.core.middleware;

import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import org.jerverless.core.middleware.middleware.CorsMiddleware;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class MiddlewareProcessor {
    private static MiddlewareProcessor instance = null;
    private static ArrayList<FunctionMiddleware> middlewareList;
    private static FunctionServer serverContext;

    public MiddlewareProcessor(FunctionServer server) {
        serverContext = server;
        middlewareList = new ArrayList();
        if(serverContext.getConfig().getCorsConfig().isCorsEnabled())
            addMiddleware(new CorsMiddleware());
        
    }
    
    public void addMiddleware(FunctionMiddleware mid) {
        middlewareList.add(mid);
    }
    
    public void resolve(HttpExchange httpExchange) {
        for(FunctionMiddleware mid : getMiddlewareList()) {
            mid.resolve(httpExchange);
        }
    }

    public ArrayList<FunctionMiddleware> getMiddlewareList() {
        return middlewareList;
    }
    
    public static MiddlewareProcessor getInstance(FunctionServer server) {
        if(instance == null)
            instance = new MiddlewareProcessor(server);
        return instance;
    }
    
}
