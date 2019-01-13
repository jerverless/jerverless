package org.jerverless.core.mappers.outputmappers;


import org.jerverless.core.mappers.outputmappers.mappers.OutputMapper;

public interface IOutputMapperProcessor {
    public void addOutputMapper(OutputMapper outputMapper);
    public String apply(String Output);
}
