/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.console;

import java.util.Scanner;

/**
 *
 * @author shalithasuranga
 */
public class ServerConsole {
    
    private static ServerConsole instance = null;
    private static Scanner scannerInstance = null;
    
    public ServerConsole() {
        scannerInstance = new Scanner(System.in);
    }
    
    
    public static ServerConsole getInstance() {
        if(instance == null)
            instance = new ServerConsole();
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
                    String s = getScannerInstance().nextLine();
                    System.out.println(s);
                }
            }
            
        }.start();
        
        
    }
}
