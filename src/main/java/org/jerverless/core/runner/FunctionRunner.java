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

package org.jerverless.core.runner;

import com.sun.net.httpserver.HttpExchange;
import org.jerverless.config.app.Route;
import org.jerverless.core.mappers.inputmappers.InputMapperProcessor;
import org.jerverless.core.mappers.outputmappers.OutputMapperProcessor;
import org.jerverless.core.server.FunctionServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalithasuranga
 * @author Kasun Vithanage
 */
public class FunctionRunner implements IFunctionRunner {
    private Route route;

    public FunctionRunner(Route route) {
        this.route = route;
    }

    public FunctionRunnerResponse exec(HttpExchange he) throws IOException {
        StringBuilder out = new StringBuilder();
        StringBuilder err = new StringBuilder();
        StringBuilder post = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        String outData;
        
        BufferedReader httpPostData = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        String postLine;
        while((postLine = httpPostData.readLine())!= null) {
            post.append(postLine);
        }
        httpPostData.close();

        String postDataTmp = post.toString();
        post.setLength(0);
        post.append(InputMapperProcessor.getInstance(FunctionServer.getInstance()).apply(postDataTmp));

        Process pr = runtime.exec(route.getCommandToArray());
        pr.getOutputStream().write(post.toString().getBytes());
        pr.getOutputStream().close();
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String outputLine;
        while((outputLine = br.readLine()) != null) {
            out.append(outputLine + '\n');
        }
        br.close();
        
        BufferedReader er = new BufferedReader(new InputStreamReader(pr.getErrorStream()));


        String outputErrorLine;
        while((outputErrorLine = er.readLine()) != null) {
            err.append(outputErrorLine + '\n');
        }
        if(err.length() > 0) 
            Logger.getLogger(FunctionServer.class.getName()).log(Level.SEVERE, 
            err.toString());
        er.close();

        outData = OutputMapperProcessor.getInstance(FunctionServer.getInstance()).apply(out.toString());
        return new FunctionRunnerOutput(outData);
    }
    
}
