/*
 * MIT License
 *
 * Copyright (c) 2019 Jerverless Org.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.jerverless.core.middleware;

import com.sun.net.httpserver.HttpExchange;
import org.jerverless.config.app.AppConfig;
import org.jerverless.config.app.Route;
import org.jerverless.core.middleware.middleware.ContentTypeMiddleware;
import org.jerverless.core.middleware.middleware.CorsMiddleware;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author shalithasuranga
 * @author Kasun Vithanage
 */
public final class MiddlewareProcessor {
    private static MiddlewareProcessor instance = null;

    private static ArrayList<FunctionMiddleware> globalMiddlewares = new ArrayList<>();
    private static HashMap<String, ArrayList<FunctionMiddleware>> middlewares = new HashMap<>();

    // TODO use DependencyInjection to inject appConfig
    private AppConfig appConfig;

    public MiddlewareProcessor(AppConfig appConfig) {
        this.appConfig = appConfig;

        if (appConfig.isCorsEnabled())
            registerGlobalMiddleware(new CorsMiddleware(appConfig.getCors()));
    }

    public synchronized void registerGlobalMiddleware(FunctionMiddleware middleware) {
        globalMiddlewares.add(middleware);
    }

    public synchronized void registerMiddleware(String route, FunctionMiddleware middleware) {
        // TODO check if same middleware registered more than once
        if (!middlewares.containsKey(route))
            middlewares.put(route, new ArrayList<>());

        middlewares.get(route).add(middleware);
    }

    // TODO replace this with a more generic mechanism

    /**
     * Helper method that allows to register a set of middlewares for a route
     *
     * @param route Route to register
     */
    public synchronized void registerMiddlewares(Route route) {
        String endpoint = route.getEndpoint();
        if (route.getCors() != null)
            registerMiddleware(endpoint, new CorsMiddleware(route.getCors()));

        if (route.getContentType() != null)
            registerMiddleware(endpoint, new ContentTypeMiddleware(route.getContentType()));
    }

    public void resolve(String route, HttpExchange httpExchange) {
        // lets fireup global middlewares
        for (FunctionMiddleware middleware : globalMiddlewares) {
            middleware.resolve(httpExchange);
        }

        // if they are overridden do it here
        if (middlewares.containsKey(route)) {
            for (FunctionMiddleware middleware : middlewares.get(route)) {
                middleware.resolve(httpExchange);
            }
        }
    }
}
