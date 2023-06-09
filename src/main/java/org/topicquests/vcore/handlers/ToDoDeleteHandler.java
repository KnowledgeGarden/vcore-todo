package org.topicquests.vcore.handlers;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.Tuple;
import org.topicquests.vcore.api.IQueries;

public class ToDoDeleteHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoDeleteHandler(final Router parent, final SqlClient db)  {
        super(db);
    }
    @Override
    public void handle(RoutingContext context) {
        System.out.println("Delete: "+context.body().asString());
        // 1
        int id = Integer.parseInt(context.body().asString());
        db
                .preparedQuery(IQueries.REMOVE_ITEM)
                .execute(Tuple.of(new Integer(id)), ar -> {
                    if (ar.succeeded()) {
                        System.out.println("DeleteDone");

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
