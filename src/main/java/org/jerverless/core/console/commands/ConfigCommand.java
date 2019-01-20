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

package org.jerverless.core.console.commands;

import org.jerverless.config.app.AppConfig;
import org.jerverless.config.app.Route;
import org.jerverless.core.console.ServerConsole;

import java.util.List;

/**
 * @author Daxter44
 */
public class ConfigCommand extends ConsoleCommand {

    public static final String COMMAND = "config";
    public static final String DESC = "Show current Configuration";
    private String Output = null;
    private AppConfig appConfig = null;

    public ConfigCommand(ServerConsole console) {
        super(console);
    }
    private String generateOutput() {
        if(Output == null) {
            appConfig = consoleContext.getServerContext().getAppConfig();
            Output = "";
            Output ="Current configuration : " +
                    "\n\tport="+ appConfig.getPort() +
                    "\n\tcors" +"="+ appConfig.getCors() +
                    "\n\troutes=\n"+ getRoutesDesc(appConfig.getRoutes());
        }
        return Output;
    }
    private String getRoutesDesc(List<Route> routes ){
        String routesDesc = "";
        int routeNumber = 0 ;
        for(Route route:routes){
            routeNumber ++;
            routesDesc += "\t\t{Route" + routeNumber;
            routesDesc += "\n\t\tendpoint=" + route.getEndpoint();
            routesDesc += "\n\t\tcommand=" + route.getCommand();
            routesDesc += "\n\t\tmethod=" + route.getMethod();
            routesDesc += "\n\t\tcontentType=" + route.getContentType();
            routesDesc += "\n\t\tcors=" + route.getCors();
            routesDesc +="}\n";
        }
        return  routesDesc;
    }
    @Override
    public void exec() {
        System.out.println(generateOutput());
    }
}