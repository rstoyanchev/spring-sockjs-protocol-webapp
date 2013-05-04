set -v

mvn -DskipTests clean package

# Change the line below to the location of Tomcat built from trunk
TOMCAT=~/dev/sources/apache/tomcat/trunk/output/build

rm -rf $TOMCAT/webapps/spring-sockjs-protocol-webapp*

cp target/spring-sockjs-protocol-webapp.war $TOMCAT/webapps/

$TOMCAT/bin/shutdown.sh
sleep 3

$TOMCAT/bin/startup.sh
