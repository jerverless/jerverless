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
public class RestartCommand extends ConsoleCommand {

    public RestartCommand(ServerConsole console) {
        super(console);
    }

    @Override
    public void exec() {
        consoleContext.getServerContext().restart();
    }
    
}
