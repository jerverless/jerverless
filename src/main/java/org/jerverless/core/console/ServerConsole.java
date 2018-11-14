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

package org.jerverless.core.console;

import java.util.Scanner;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class ServerConsole {
    
    private static ServerConsole instance = null;
    private static Scanner scannerInstance = null;
    private static CommandFactory commadFactory = null;
    private static FunctionServer serverContext = null;
    
    public ServerConsole(FunctionServer server) {
        scannerInstance = new Scanner(System.in);
        commadFactory = new CommandFactory(this);
        serverContext = server;
        System.out.println(serverContext);
    }

    public CommandFactory getCommadFactory() {
        return commadFactory;
    }

    public FunctionServer getServerContext() {
        return serverContext;
    }
    
    
    public static ServerConsole getInstance(FunctionServer server) {
        if(instance == null)
            instance = new ServerConsole(server);
        return instance;
    }

    public static Scanner getScannerInstance() {
        return scannerInstance;
    }
    
    public void openStream() {
        
        new Thread(){
            @Override
            public void run() {
                while(true) {
                    System.out.print("> ");
                    System.out.flush();
                    String cmd = getScannerInstance().nextLine();
                    commadFactory.make(cmd).exec();
                }
            }
            
        }.start();
        
        
    }
}
