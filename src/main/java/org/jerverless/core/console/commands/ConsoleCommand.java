/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.console.commands;

import org.jerverless.core.console.ServerConsole;

/**
 *
 * @author shalithasuranga
 */
public abstract class ConsoleCommand {
    
    private static String COMMAND = "generic";
    protected ServerConsole consoleContext = null;
    
    protected ConsoleCommand(ServerConsole console) {
        this.consoleContext  = console;
    }
    
    public abstract void exec();
}
