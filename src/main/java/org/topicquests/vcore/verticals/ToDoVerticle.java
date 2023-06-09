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
package org.topicquests.vcore.verticals;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.SqlClient;
import org.topicquests.vcore.handlers.ToDoDeleteHandler;
import org.topicquests.vcore.handlers.ToDoGetHandler;
import org.topicquests.vcore.handlers.ToDoPostHandler;
import org.topicquests.vcore.handlers.ToDoPutHandler;

/**
 * Here to show how it's done- can remove this
 * @author jackpark
 * <p>
 *     This Verticle serves ajax calls from a Vue.js Frontside ("app")
 * </p>
 */
public class ToDoVerticle {

    ///////////////////////////
    // routing for https://github.com/gazi-dis/vue3-todo-withapi
    // $axios.get("/todos")
    //  vertx
    // axios.post("/todos", this.newTodo
    // axios.delete("/todos/" + index
    // axios.put("/todos/" + todo.id, { "todo": todo.todo, "done": !todo.done }
    //      mark existing todo as done
    ///////////////////////////
    public static void attach(final Router parent, final SqlClient db, final Vertx vertx) {
        System.out.println("ToDoVerticle booted");
        final String path = "/todos";
        parent.get(path).handler(new ToDoGetHandler(parent, db));

        final String updatepath = "/todos/update";
        parent.post(updatepath).handler(new ToDoPutHandler(parent, db));

        final String deletepath = "/todos/delete";
        parent.post(deletepath).handler(new ToDoDeleteHandler(parent, db));

        final String postpath = "/todos";
        parent.post(postpath).handler(new ToDoPostHandler(parent, db));

    }

}
