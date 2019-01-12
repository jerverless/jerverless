package org.jerverless.core.mappers.inputmappers;

import org.jerverless.core.mappers.inputmappers.mappers.InputMapper;

public interface IInputMapperProcessor {
    public void addInputMapper(InputMapper inputMapper);
    public String apply(String input);
}
