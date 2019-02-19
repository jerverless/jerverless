/*
 * MIT License
 *
 * Copyright (c) 2019 Jerverless Org.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.jerverless.core.console.commands;

import org.jerverless.core.console.ServerConsole;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalithasuranga
 */
public class HelpCommand extends ConsoleCommand {

    public static final String COMMAND = "help";
    public static final String DESC = "Display CLI manual.";
    private static String Output = null;
    
    public HelpCommand(ServerConsole console) {
        super(console);
    }
    
    private String generateOutput() {
        if(Output == null) {
            Output = "";
            for(ConsoleCommand cmd : consoleContext.getSupportedCommands()) {
                try {
                    Class cmdClass = cmd.getClass();
                    Output += cmdClass.getDeclaredField("COMMAND").get(null) + "\t" + 
                            cmdClass.getDeclaredField("DESC").get(null) + "\n";
                } catch (Exception ex) {
                    Logger.getLogger(HelpCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return Output;
    }

    public String getOutput() {
        return Output;
    }

    @Override
    public void exec() {
        System.out.println(generateOutput());
    }
    
}
