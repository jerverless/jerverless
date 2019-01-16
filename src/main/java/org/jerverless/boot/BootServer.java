/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.boot;

import org.jerverless.core.server.FunctionServer;

/**
 *
 * Boots the jerverless server with command line
 */
public class BootServer {
    /**
     * main method 
     * 
     * @param args 
     * @return 
     */
    public static void main(String[] args) {
        System.out.println("Bootstrapping server...");
        FunctionServer.create().start();
    }
}
