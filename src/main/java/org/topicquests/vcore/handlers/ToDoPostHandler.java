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
package org.topicquests.vcore.handlers;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.*;
import org.topicquests.vcore.api.IQueries;

/**
 * @author jackpark
 */
public class ToDoPostHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoPostHandler(final Router parent, final SqlClient db) {
        super(db);

    }

    @Override
    public void handle(RoutingContext context) {
        System.out.println("POST: "+context.body().asString());
        JsonObject json  = context.body().asJsonObject();
        String todo = json.getString("todo");
        boolean done = json.getBoolean("done");
        System.out.println("POST2: "+todo+" "+done);

        db
                .preparedQuery(IQueries.INSERT_ITEM)
                .execute(Tuple.of(todo, new Boolean(done)), ar -> {
                    if (ar.succeeded()) {
                        System.out.println("PostDone");

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
