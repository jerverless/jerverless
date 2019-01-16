package org.jerverless.config;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void shouldLoadYAMLFromPath() {
        Path confFile = Paths.get("src", "test", "resources", ".jerverless");
        try {
            Configuration configuration = new Configuration(confFile.toString());

            // test for port
            assertEquals(
                    "Should port equal to 8080",
                    8080,
                    configuration.getAppConfig().getPort()
            );

            // test for two entries

            assertEquals(
                    "should get two endpoints",
                    configuration.getAppConfig().getEndpoints().size(),
                    2
            );

            // test endpoint 1
            assertEquals(
                    "should get route /foo on 1st endpoint",
                    configuration.getAppConfig().getEndpoints().get(0).getRoute(),
                    "/foo"
            );

            assertEquals(
                    "should get method GET on 1st endpoint",
                    configuration.getAppConfig().getEndpoints().get(0).getMethod(),
                    "GET"
            );

            assertEquals(
                    "should get command `test --foo` on 1st endpoint",
                    configuration.getAppConfig().getEndpoints().get(0).getCommand(),
                    "test --foo"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = java.io.IOException.class)
    public void shouldThrowErrorWhenFileNotPresent() throws IOException {
        Configuration configuration = new Configuration("lorem ipsum");
    }
}
