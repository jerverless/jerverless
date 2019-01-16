package org.jerverless.config.app;

import java.util.List;

public interface Configuration {
    int getPort();

    String getCors();

    boolean isCorsEnabled();

    List<Route> getRoutes();
}
