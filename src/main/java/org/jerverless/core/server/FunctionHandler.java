/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import org.jerverless.core.runner.FunctionRunner;

/**
 *
 * @author shalithasuranga
 */
public class FunctionHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {
        String out = new FunctionRunner().exec().getContent();
        he.sendResponseHeaders(200, out.length());
        OutputStream os = he.getResponseBody();
        
        
        os.write(out.getBytes());
        os.close();
        
                
    }
    
}
