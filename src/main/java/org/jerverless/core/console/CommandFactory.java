/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.core.console;

import org.jerverless.core.console.commands.ConsoleCommand;
import org.jerverless.core.console.commands.HelpCommand;
import org.jerverless.core.console.commands.RestartCommand;
import org.jerverless.core.console.commands.StopCommand;
import org.jerverless.core.console.commands.UnknownCommand;

/**
 *
 * @author ShalithaS
 */
public class CommandFactory {
    
    private static ServerConsole consoleContext = null;

    public CommandFactory(ServerConsole console) {
        consoleContext = console;
    }
    
    public ConsoleCommand make(String input) {
        if(input.equals("restart")) {
            return new RestartCommand(consoleContext);
        }
        else if (input.equals("help")) {
            return new HelpCommand(consoleContext);
        }
        else if (input.equals("stop")) {
            return new StopCommand(consoleContext);
        }
        return new UnknownCommand(consoleContext);
    }
    
}
