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

package org.jerverless.core.mappers.outputmappers;

import org.jerverless.core.mappers.outputmappers.mappers.DefaultOutputMapper;
import org.jerverless.core.mappers.outputmappers.mappers.OutputMapper;
import org.jerverless.core.server.FunctionServer;

import java.util.ArrayList;

public class OutputMapperProcessor implements IOutputMapperProcessor {
    private static OutputMapperProcessor instance = null;
    private ArrayList<OutputMapper> outputMappers;
    private FunctionServer serverContext = null;

    private OutputMapperProcessor(FunctionServer server) {
        serverContext = server;
        outputMappers = new ArrayList<>();
        addOutputMapper(new DefaultOutputMapper());
    }

    public static OutputMapperProcessor getInstance(FunctionServer server) {
        if(instance == null) {
            instance = new OutputMapperProcessor(server);
        }
        return instance;
    }

    public void addOutputMapper(OutputMapper inputMapper) {
        outputMappers.add(inputMapper);
    }

    @Override
    public String apply(String input) {
        for(OutputMapper im : outputMappers) {
            input = im.transform(input);
        }
        return input;
    }
}
