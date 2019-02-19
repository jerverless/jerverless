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
