/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.boot;

import org.jerverless.config.loaders.YAMLConfigLoader;
import org.jerverless.core.server.FunctionServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Boots the jerverless server with command line
 */
public class Main {
    /**
     * main method
     *
     * @param args
     * @return
     */
    public static void main(String[] args) {
        try {
            System.out.println("Bootstrapping server...");

            // TODO use DependencyInjection for seperation of concerns
            YAMLConfigLoader YAMLConfigLoader = new YAMLConfigLoader("jerverless.yml");

            FunctionServer functionServer = new FunctionServer(YAMLConfigLoader.getAppConfig());
            functionServer.start();
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
                    null, e);
            System.exit(1);
        }
    }
}
