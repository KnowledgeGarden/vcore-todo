package org.topicquests.vcore.handlers;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.Tuple;
import org.topicquests.vcore.api.IQueries;

public class ToDoPutHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoPutHandler(final Router parent, final SqlClient db) {
        super(db);
    }
    @Override
    public void handle(RoutingContext context) {
        System.out.println("Put: "+context.body().asString());
        //{"id":1,"done":true}
        JsonObject json = context.body().asJsonObject();
        int id = json.getInteger("id");
        boolean done = json.getBoolean("done");
        db
                .preparedQuery(IQueries.UPDATE_ITEM)
                .execute(Tuple.of(new Boolean(done), new Integer(id)), ar -> {
                    if (ar.succeeded()) {
                        System.out.println("UpdateDone");

                        context.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                                .setStatusCode(200)
                                .end();
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });

    }
}
