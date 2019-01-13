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
        outputMappers = new ArrayList();
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
