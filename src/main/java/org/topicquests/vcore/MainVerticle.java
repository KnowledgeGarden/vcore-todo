package org.topicquests.vcore;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.sqlclient.SqlClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.topicquests.vcore.config.Configurator;
import org.topicquests.vcore.db.PgDriver;
import org.topicquests.vcore.verticals.ToDoVerticle;

/**
 * @author jackpark
 * @license Apache2
 */
public class MainVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
    private Configurator config;
    private JsonObject configProperties;
    private SqlClient db;

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        LOG.debug("Starting");
        System.out.println("Starting");
        config = new Configurator(vertx);
        configProperties = config.getProperties();
        db = PgDriver.createPgPool(config, vertx);
        startServer(startPromise);
    }

    private void startServer(final Promise<Void> startPromise) {
        int port = config.getServerPort();
        final Router restApi = Router.router(vertx);
        restApi.route()
                .handler(BodyHandler.create())
                .failureHandler(handleFailure());
        // simple test which fires webroot/index.html to test
        //restApi.route("/*").handler(StaticHandler.create());
        // fire up our routes
        ToDoVerticle.attach(restApi, db, vertx);
        vertx.createHttpServer()
                .requestHandler(restApi)
                .exceptionHandler(error -> LOG.error("HTTP Server error: ", error))
                .listen(port, http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        LOG.info("HTTP server started on port {}", port);//configuration.getServerPort());
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }

    private Handler<RoutingContext> handleFailure() {
        return errorContext -> {
            if (errorContext.response().ended()) {
                // Ignore completed response
                return;
            }
            LOG.error("Route Error:", errorContext.failure());
            errorContext.response()
                    .setStatusCode(500)
                    .end(new JsonObject().put("message", "Something went wrong :(").toBuffer());
        };
    }
    public static void main(String [] args) {
    //TODO
    }
}
