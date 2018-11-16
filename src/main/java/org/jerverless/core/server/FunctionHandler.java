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

package org.jerverless.core.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import org.jerverless.core.middleware.MiddlewareProcessor;
import org.jerverless.core.runner.FunctionRunner;

/**
 *
 * @author shalithasuranga
 */
public class FunctionHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {
        String out = new FunctionRunner().exec(he).getContent();
        
        MiddlewareProcessor.getInstance(FunctionServer.getInstance()).resolve(he);
        
        he.sendResponseHeaders(200, out.length());
        OutputStream os = he.getResponseBody();
        
        
        os.write(out.getBytes());
        os.close();
        
                
    }
    
}
