package org.topicquests.vcore.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.SqlClient;

public class ToDoPostHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoPostHandler(final Router parent, final SqlClient db, Vertx vertx) {
        super(db, vertx);

    }

    @Override
    public void handle(RoutingContext context) {



    }
}
