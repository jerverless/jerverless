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

import org.jerverless.config.app.AppConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kasun Vithanage
 */
public class YAMLConfigLoader implements ConfigLoader {
    String path;
    AppConfig appConfig;

    public YAMLConfigLoader(String path) throws IOException {
        this.path = path;
        Yaml yaml = new Yaml(new Constructor(AppConfig.class));

        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);

        appConfig = (AppConfig) yaml.load(inputStream);
    }

    public String getPath() {
        return path;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    @Override
    public String toString() {
        return "YAMLConfigLoader{" +
                "path='" + path + '\'' +
                ", appConfig=" + appConfig +
                '}';
    }
}
