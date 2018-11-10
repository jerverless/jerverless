/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author shalithasuranga
 */
public class FunctionRunner implements IFunctionRunner {

    public FunctionRunnerResponse exec() throws IOException {
        StringBuilder out = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        
        Process pr = runtime.exec("ls");
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        
        String outputLine = null;
        while((outputLine = br.readLine()) != null) {
            out.append(outputLine + '\n');
        }
        return new FunctionRunnerOutput(out.toString());
    }
    
}
