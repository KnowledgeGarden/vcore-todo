package org.topicquests.vcore.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;

/**
 * @author jackpark
 * @license Apache2
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
