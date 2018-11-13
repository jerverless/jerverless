/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.boot.config;

/**
 *
 * @author shalithasuranga
 */
public class ConfigServerlessCommand extends IConfigProperty {
    private String[] commands;
    
    public ConfigServerlessCommand(String commandSet) {
        this.commands = commandSet.split(" ");
    }

    public String[] getCommands() {
        return commands;
    }
}
