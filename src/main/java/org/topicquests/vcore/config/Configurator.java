/*
 *
 * Copyright (C) 2023, TopicQuests Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.topicquests.vcore.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;

/**
 * @author jackpark
 */
public class Configurator {
    private static final Logger LOG = LoggerFactory.getLogger(Configurator.class);
    private final String PATH = "config/config.json";
    private JsonObject properties=null;

    public Configurator(Vertx vertx) {
        try {
            String jsonStr = IOUtils.toString(new FileReader(PATH));
            System.out.println(jsonStr);
            properties = new JsonObject(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject getProperties() {
        return properties;
    }

    public JsonObject getDataServerProperties() {
        return properties.getJsonObject("DataServer");
    }

    public int getServerPort() { return properties.getInteger("ServerPort"); };
}
