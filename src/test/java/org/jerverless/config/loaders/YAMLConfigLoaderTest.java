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
