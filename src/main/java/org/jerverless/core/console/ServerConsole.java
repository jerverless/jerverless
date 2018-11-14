/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
