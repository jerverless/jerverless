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

package org.jerverless.core.mappers.inputmappers;

import org.jerverless.core.mappers.inputmappers.mappers.DefaultInputMapper;
import org.jerverless.core.mappers.inputmappers.mappers.InputMapper;
import org.jerverless.core.server.FunctionServer;

import java.util.ArrayList;

public class InputMapperProcessor implements IInputMapperProcessor {
    private static InputMapperProcessor instance = null;
    private ArrayList<InputMapper> inputMappers;
    private FunctionServer serverContext = null;

    private InputMapperProcessor(FunctionServer server) {
        serverContext = server;
        inputMappers = new ArrayList<>();
        addInputMapper(new DefaultInputMapper());
    }

    public static InputMapperProcessor getInstance(FunctionServer server) {
        if(instance == null) {
            instance = new InputMapperProcessor(server);
        }
        return instance;
    }

    public void addInputMapper(InputMapper inputMapper) {
        inputMappers.add(inputMapper);
    }

    @Override
    public String apply(String input) {
        for(InputMapper im : inputMappers) {
            input = im.transform(input);
        }
        return input;
    }
}
