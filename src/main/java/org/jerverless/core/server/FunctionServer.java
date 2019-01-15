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

package org.jerverless.core.server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jerverless.boot.config.ConfigServerlessCommand;
import org.jerverless.boot.config.ServerConfig;
import org.jerverless.core.console.ServerConsole;
import org.jerverless.core.mappers.inputmappers.InputMapperProcessor;
import org.jerverless.core.mappers.outputmappers.OutputMapperProcessor;
import org.jerverless.core.mappers.outputmappers.mappers.OutputMapper;
import org.jerverless.core.middleware.MiddlewareProcessor;

/**
 *
 * @author shalithasuranga
 */
public class FunctionServer implements IFunctionServer {
    
    private static HttpServer serverInstance = null;
    private static FunctionServer instance = null;
    private static ServerConsole consoleInstance = null;
    private static ServerConfig config = null;
    private static MiddlewareProcessor middlewareProcessor = null;
    private static InputMapperProcessor inputMapperProcessor = null;
    private static OutputMapperProcessor outputMapperProcessor = null;
    
    public FunctionServer(){
        try {
            config = ServerConfig.create();
            serverInstance = HttpServer.create(new InetSocketAddress(config.getFunctionPort().getPort()), 0);
            serverInstance.setExecutor(Executors.newCachedThreadPool()); 
            // unlimited thread pool! warn TODO : replace with fixed
            for(ConfigServerlessCommand commandConfig : config.getRoutesConfig().getRoutes()) {
                serverInstance.createContext( commandConfig.getEndpoint(), new FunctionHandler(commandConfig));
            }

            consoleInstance = ServerConsole.getInstance(this);
            middlewareProcessor = MiddlewareProcessor.getInstance(this);
            inputMapperProcessor = InputMapperProcessor.getInstance(this);
            outputMapperProcessor = OutputMapperProcessor.getInstance(this);
            
        } catch (IOException ex) {
            Logger.getLogger(FunctionServer.class.getName()).log(Level.SEVERE, 
                    null, ex);
            Logger.getLogger(FunctionServer.class.getName()).log(Level.SEVERE,
                    "jerverless can't bind in to given port");
            System.exit(0);
        }
    }

    public ServerConfig getConfig() {
        return config;
    }
    
    public static FunctionServer create() {
        if(instance == null) {
            instance = new FunctionServer();
        }
        return instance;
    }
    
    public void start() {
        getServerInstance().start();
        awesomeAscii();
        getConsoleInstance().openStream();
    }

    public static HttpServer getServerInstance() {
        return serverInstance;
    }

    public static FunctionServer getInstance() {
        return instance;
    }

    public static ServerConsole getConsoleInstance() {
        return consoleInstance;
    }

    public void stop() {
        System.out.println("Shutting down..");
        FunctionServer.getServerInstance().stop(0);
        FunctionServer.getConsoleInstance().closeStream();
        System.out.println("Server stopped.");
        System.exit(0);

    }

    public void restart() {
        System.out.println("Restarting..");
        // TODO : Implement restart logic!
    }
    
    public void awesomeAscii() {
        String ascii = "   _                          _               \n" +
                    "  (_)                        | |              \n" +
                    "   _  ___ _ ____   _____ _ __| | ___  ___ ___ \n" +
                    "  | |/ _ \\ '__\\ \\ / / _ \\ '__| |/ _ \\/ __/ __|\n" +
                    "  | |  __/ |   \\ V /  __/ |  | |  __/\\__ \\__ \\\n" +
                    "  | |\\___|_|    \\_/ \\___|_|  |_|\\___||___/___/\n" +
                    " _/ |                                         \n" +
                    "|__/ ";
        System.out.println(ascii + "\n\n");
    }
}
