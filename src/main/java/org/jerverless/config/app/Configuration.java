package org.jerverless.config.app;

import java.util.List;

/**
 * @author Kasun Vithanage
 */

public interface Configuration {
    int getPort();

    String getCors();

    boolean isCorsEnabled();

    List<Route> getRoutes();
}
