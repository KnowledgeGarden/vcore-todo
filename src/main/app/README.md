# Vert.x Vue ToDo App
Inspired by, and cloned from this project:<br/>
[vue3-todo-withapi](https://github.com/gazi-dis/vue3-todo-withapi)

## Building
Ideally, there is a Maven plugin for the main Java server which
will build this, but it's not running (yet). For now:<br/>
- <code>npm install</code>
- <code>npm build</code>
<br/>
That's it! Now you are ready to build the server with<br/> 
<code>mvn clean install exec:java</code>
## About
This is a test application for the _vcore_ project as a base
in which we use that code as a template for _vcore-todo_, the
first in a line of experiments with the [VERT.X](https://vertx.io/)
platform.

The vcore-todo <code>MainVerticle</code> class will:
- Create a router
- Create a server
- Use the router to boot this Vue app.
- From there, everything is handled by the router and its
PostgreSQL database