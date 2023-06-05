package org.topicquests.vcore.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.SqlClient;

public class ToDoGetHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoGetHandler(final Router parent, final SqlClient db, Vertx vertx) {
        super(db, vertx);
    }

    @Override
    public void handle(RoutingContext context) {
        System.out.println("ToDoGetHandler fired");
        Integer limit = 10; // default
        Integer offset = 0; // default
        String l = context.request().getParam("limit");
        String o = context.request().getParam("offset");
        if (l != null) {
            limit = Integer.valueOf(l);
            offset = Integer.valueOf(o);
        }

        // testing
        JsonObject ctx = new JsonObject();
        engine.render(ctx, "templates/test.hbs", rx -> {
            System.out.println("FOO");
            if (rx.succeeded()) {
                context.response().end(rx.result());
            } else {
                context.fail(rx.cause());
            }
        });
    }
}
