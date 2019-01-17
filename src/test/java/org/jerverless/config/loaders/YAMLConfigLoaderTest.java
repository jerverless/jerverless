package org.jerverless.config.loaders;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class YAMLConfigLoaderTest {
    @Test
    public void shouldLoadYAMLFromPath() {
        Path confFile = Paths.get("src", "test", "resources", "jerverless.yml");
        try {
            YAMLConfigLoader YAMLConfigLoader = new YAMLConfigLoader(confFile.toString());

            // test for port
            assertEquals(
                    "Should port equal to 8080",
                    8080,
                    YAMLConfigLoader.getAppConfig().getPort()
            );

            // test for two entries
            assertEquals(
                    "should get two endpoints",
                    YAMLConfigLoader.getAppConfig().getRoutes().size(),
                    2
            );

            // test endpoint 1
            assertEquals(
                    "should get route /foo on 1st endpoint",
                    YAMLConfigLoader.getAppConfig().getRoutes().get(0).getEndpoint(),
                    "/foo"
            );

            // test method on endpoint 1
            assertEquals(
                    "should get method GET on 1st endpoint",
                    YAMLConfigLoader.getAppConfig().getRoutes().get(0).getMethod(),
                    "GET"
            );

            // test commands
            assertEquals(
                    "should get command `test --foo` on 1st endpoint",
                    YAMLConfigLoader.getAppConfig().getRoutes().get(0).getCommand(),
                    "test --foo"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = java.io.IOException.class)
    public void shouldThrowErrorWhenFileNotPresent() throws IOException {
        YAMLConfigLoader YAMLConfigLoader = new YAMLConfigLoader("lorem ipsum");
    }
}
