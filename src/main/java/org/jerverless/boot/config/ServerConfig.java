/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.boot.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class ServerConfig {
    private static final String FILENAME = "jerverless.properties";
    private static Properties config = null;
    private static ServerConfig instance = null;
    private static ConfigServerlessCommand functionCommand = null;
    private static ConfigPort functionPort = null;

    private ServerConfig() {
        config = new Properties();
        FileInputStream propFile = null;
        try {
            propFile = new FileInputStream(FILENAME);
            config.load(propFile);
            functionCommand = new ConfigServerlessCommand(config.getProperty("exec"));
            functionPort = new ConfigPort(config.getProperty("port", "8080"));
        } catch (IOException ex) {
            Logger.getLogger(ServerConfig.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        

    }

    public ConfigPort getFunctionPort() {
        return functionPort;
    }
    
    public static ServerConfig create() {
        if(instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    public ConfigServerlessCommand getFunctionCommand() {
        return functionCommand;
    }

    
}
