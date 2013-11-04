## Overview

This is a simple web application that can be used as the server when running the [SockJS protocol](https://github.com/sockjs/sockjs-protocol) tests. It is built on the Spring WebSocket and SockJS support, currently in development for Spring Framework 4.0, and can be run on one of the servers listed further below.

Before running the tests, set the following environment variable:

    export SOCKJS_URL=http://localhost:8080/spring-sockjs-protocol-webapp

### Known Issues with Protocol Tests

WebsocketHixie76/WebsocketHybi10/RawWebsocket: Java Servlet containers do not support older versions of the WebSocket protocol. See [issue 72](https://github.com/sockjs/sockjs-protocol/issues/72).

HandlingClose: Servlet containers do not detect a client disconnect soon enough. The the next message (or heartbeat) after the disconnect will fail and at that point the session will be closed. See [SERVLET_SPEC-44](https://java.net/jira/browse/SERVLET_SPEC-44)

JSONEncoding: this test requires a recent version of Jackson (see [JACKSON-884](http://jira.codehaus.org/browse/JACKSON-884))

### Tomcat 8

Check the [Tomcat home page](http://tomcat.apache.org/) for the latest Tomcat 8 release, currently `RC1 alpha`. 

After unzipping Tomcat 8, set `TOMCAT8_HOME` as an environment variable and use [deployTomcat8.sh](https://github.com/rstoyanchev/spring-sockjs-protocol-webapp/blob/master/deployTomcat8.sh) and [shutdownTomcat8.sh](https://github.com/rstoyanchev/spring-sockjs-protocol-webapp/blob/master/shutdownTomcat8.sh) in this directory.

### Jetty 9

The easiest way to run on Jetty 9 (currently 9.0.5):

    mvn jetty:run

**Note:** To deploy to a Jetty installation, add this to Jetty's `start.ini`:

    OPTIONS=plus
    etc/jetty-plus.xml
    OPTIONS=annotations
    etc/jetty-annotations.xml

### Glassfish

After unzipping Glassfish 4 start the server:

    <unzip_dir>/glassfish4/bin/asadmin start-domain

Set `GLASSFISH4_HOME` as an environment variable and use [deployGlassfish.sh](https://github.com/rstoyanchev/spring-sockjs-protocol-webapp/blob/master/deployGlassfish.sh) in this directory.




