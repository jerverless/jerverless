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
    private static CorsConfig corsConfig = null;

    private ServerConfig() {
        config = new Properties();
        FileInputStream propFile = null;
        try {
            propFile = new FileInputStream(FILENAME);
            config.load(propFile);
            functionCommand = new ConfigServerlessCommand(config.getProperty("exec"));
            functionPort = new ConfigPort(config.getProperty("port", "8080"));
            corsConfig = new CorsConfig(config.getProperty("cors", "false"));
        } catch (IOException ex) {
            Logger.getLogger(ServerConfig.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        

    }

    public CorsConfig getCorsConfig() {
        return corsConfig;
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
