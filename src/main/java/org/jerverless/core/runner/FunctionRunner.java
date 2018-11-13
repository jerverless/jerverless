/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.runner;

import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class FunctionRunner implements IFunctionRunner {

    public FunctionRunnerResponse exec(HttpExchange he) throws IOException {
        StringBuilder out = new StringBuilder();
        StringBuilder err = new StringBuilder();
        StringBuilder post = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        
        BufferedReader httpPostData = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        String postLine = null;
        while((postLine = httpPostData.readLine())!= null) {
            post.append(postLine);
        }
        httpPostData.close();
        
        Process pr = runtime.exec(FunctionServer.getInstance().getConfig().getFunctionCommand().getCommands());
        pr.getOutputStream().write(post.toString().getBytes());
        pr.getOutputStream().close();
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String outputLine = null;
        while((outputLine = br.readLine()) != null) {
            out.append(outputLine + '\n');
        }
        br.close();
        
        BufferedReader er = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        if(er.lines().count() != 0) {
            String outputErrorLine = null;
            while((outputErrorLine = er.readLine()) != null) {
                err.append(outputErrorLine + '\n');
            }
            Logger.getLogger(FunctionServer.class.getName()).log(Level.SEVERE, 
                    err.toString());
        }
        er.close();

        return new FunctionRunnerOutput(out.toString());
    }
    
}
