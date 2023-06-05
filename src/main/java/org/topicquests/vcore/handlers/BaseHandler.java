package org.topicquests.vcore.handlers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine;
import io.vertx.sqlclient.SqlClient;

/**
 * Database Handlers can extend this class for convenience
 * @author jackpark
 * @license Apache2
 */
public class BaseHandler {
    protected final SqlClient db;
    protected final HandlebarsTemplateEngine engine;
    private final String INDEX_PATH = "templates/index.hbs";
    /**
     *
     * @param db
     * @param vertx here in case it's needed for template engines
     */
    public BaseHandler(final SqlClient db, Vertx vertx) {
        engine = HandlebarsTemplateEngine.create(vertx);
        this.db = db;
    }
}
