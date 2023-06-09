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
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowIterator;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;
import org.topicquests.vcore.api.IQueries;

/**
 * @author jackpark
 */
public class ToDoGetHandler extends BaseHandler implements Handler<RoutingContext> {

    public ToDoGetHandler(final Router parent, final SqlClient db) {
        super(db);
    }

    @Override
    public void handle(RoutingContext context) {
        System.out.println("ToDoGetHandler fired");
 /*       Integer limit = 10; // default
        Integer offset = 0; // default
        String l = context.request().getParam("limit");
        String o = context.request().getParam("offset");
        if (l != null) {
            limit = Integer.valueOf(l);
            offset = Integer.valueOf(o);
        }
        JsonArray data = new JsonArray();*/

        db
                .preparedQuery(IQueries.LIST_ITEMS)
                .execute()
                .onComplete(ar -> {
                    if (ar.succeeded()) {
                        System.out.println("GotData");
                        JsonArray response = new JsonArray();
                        RowSet<Row> result = ar.result();
                        RowIterator<Row> itr = result.iterator();
                        Row r;
                        while (itr.hasNext()) {
                            r = itr.next();
                            JsonObject hit = new JsonObject();
                            hit.put("id", r.getInteger("id"));
                            hit.put("todo", r.getString("todo"));
                            hit.put("done", r.getBoolean("done"));
                            response.add(hit);
                        }
                        System.out.println(response);
                        JsonObject ctx = new JsonObject();
                        ctx.put("data", response);
                        context.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                                .end(response.encode());
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });
    }
}
