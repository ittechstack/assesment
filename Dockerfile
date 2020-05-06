FROM lpicanco/java11-alpine
VOLUME /tmp
COPY target/assessment-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]