FROM openjdk:11-jre-slim

EXPOSE 8080

COPY target/auth*.jar auth.jar

ENTRYPOINT [ \
"java", \
"-jar", \
"-Xmx1g", \
"-XX:+UseG1GC", \
"-XX:MaxGCPauseMillis=50", \
"-XX:+DisableExplicitGC", \
"/auth.jar"]