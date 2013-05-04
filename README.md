
## Overview

This is a simple web application that can be used as the server when running the [SockJS protocol](https://github.com/sockjs/sockjs-protocol) tests. It is built on the Spring WebSocket and SockJS support, currently in development for Spring Framework 4.0, and can be run on one of the servers listed further below.

Before running the tests, set the following environment variable:

    export SOCKJS_URL=http://localhost:8080/spring-sockjs-protocol-webapp

### Tomcat

Tomcat provides early JSR-356 support (WebSocket API for Java). You'll need to build the latest Tomcat source from trunk, which is relatively simple.

Check out Tomcat trunk:

    mkdir tomcat
    cd tomcat
    svn co http://svn.apache.org/repos/asf/tomcat/trunk/
    cd trunk

Create `build.properties` in the trunk directory with similar content:

    # ----- Default Base Path for Dependent Packages -----
    # Replace this path with the path where dependencies binaries should be downloaded
    base.path=~/dev/sources/apache/tomcat/download

Run the ant build:

    ant clean
    ant

A usable Tomcat installation can be found in `output/build`

### Jetty 9

Download and use the latest Jetty (e.g. 9.0.2.v20130417). It does not support JSR-356 yet but that's not an issue, since we're using the Jetty 9 native WebSocket API.

If using Java-based Servlet configuration instead of web.xml, add the following options to Jetty's start.ini:

    OPTIONS=plus
    etc/jetty-plus.xml
    OPTIONS=annotations
    etc/jetty-annotations.xml

### Glassfish

Glassfish 4 provides JSR-356 support.

Download a [Glassfish 4 build](http://dlc.sun.com.edgesuite.net/glassfish/4.0/) (e.g. glassfish-4.0-b84.zip from the promoted builds)

Unzip the downloaded file.

Start the server:

    cd <unzip_dir>/glassfish4
    bin/asadmin start-domain

Deploy the WAR file and watch the logs:

    cd <unzip_dir>/glassfish4
    less `glassfish/domains/domain1/logs/server.log`


