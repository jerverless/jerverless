package org.jerverless.core.mappers.inputmappers;

import org.jerverless.core.mappers.inputmappers.mappers.InputMapper;
import org.jerverless.core.server.FunctionServer;

import java.util.ArrayList;

public class InputMapperProcessor implements IInputMapperProcessor {
    private static InputMapperProcessor instance = null;
    private ArrayList<InputMapper> inputMappers;
    private FunctionServer serverContext = null;

    private InputMapperProcessor(FunctionServer server) {
        serverContext = server;
        inputMappers = new ArrayList();
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
