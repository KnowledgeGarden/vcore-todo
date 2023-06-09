# vcore-todo
A simple ToDo app built with Vue and served by VERT.X
## Building
This is a combined Maven Java app and an NPM Node Vue app, which
is located directly in <code>src/main/app</code> of the Java system.
It is there because, when built, a directory <code>src/main/app/dist</code>
is created, and that is required for the Java application to serve.<br/>
### Building Javascript
In the long run, there is a Maven plugin  in the <code>pom.xml</code> file
which can build the javascript, but it's not running yet, so it is commented out.
For now, cd to <code>src/main/app</code> and execute these:
- <code>npm install</code>
- <code>npm run build</code>
### Building Java
In the root directory (where <code>pom.xml</code> is)
- mvn clean install exec:java
That will boot and run the system at <code>localhost:8080</code>