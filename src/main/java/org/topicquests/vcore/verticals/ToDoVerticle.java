package org.topicquests.vcore.verticals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.SqlClient;
import org.topicquests.vcore.handlers.ToDoGetHandler;
import org.topicquests.vcore.handlers.ToDoPostHandler;

/**
 * Here to show how it's done- can remove this
 * @author jackpark
 * @license Apache2
 */
public class ToDoVerticle {

    public static void attach(final Router parent, final SqlClient db, final Vertx vertx) {
        System.out.println("ToDoVerticle booted");
        final String path = "/";
        parent.get(path).handler(new ToDoGetHandler(parent, db, vertx));

        final String postpath = "/post/";
        parent.post(postpath).handler(new ToDoPostHandler(parent, db, vertx));
    }

}
