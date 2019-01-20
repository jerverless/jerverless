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

import org.jerverless.core.console.commands.*;
import org.jerverless.core.server.FunctionServer;

/**
 *
 * @author shalithasuranga
 */
public class ServerConsole {
    
    private static ServerConsole instance = null;
    private static Scanner scannerInstance = null;
    private static CommandFactory commandFactory = null;
    private static FunctionServer serverContext = null;
    private static Thread consoleThread = null;
    private static boolean stopSig = false;
    
    private static ConsoleCommand[] supportedCommands;
    
    public ServerConsole(FunctionServer server) {
        scannerInstance = new Scanner(System.in);
        commandFactory = new CommandFactory(this);
        serverContext = server;
        supportedCommands = new ConsoleCommand[] {
            new StopCommand(this),
            new RestartCommand(this),
            new ConfigCommand(this),
            new HelpCommand(this)
        };
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public ConsoleCommand[] getSupportedCommands() {
        return supportedCommands;
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
        
        consoleThread = new Thread(){
            @Override
            public void run() {
                System.out.print("> ");
                System.out.flush();
                while(!stopSig) {
                    if(getScannerInstance().hasNextLine()) {
                        String cmd = getScannerInstance().nextLine().toLowerCase();
                        commandFactory.make(cmd).exec();
                        System.out.print("> ");
                        System.out.flush();
                    }
                }
            }
            
        };
        consoleThread.start();
        
    }
    
    public void closeStream() {
        stopSig = true;
        getScannerInstance().close();
    }
    
}
