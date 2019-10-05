FROM openjdk:8-jre-alpine
RUN mkdir -p /app

# Copy jar file to app folder
COPY ./target/onixprocess-dev-0.0.1-jar-with-dependencies.jar /app

EXPOSE 5701 8080
CMD ["java","-jar", "/app/vertx-hazelcast-4.0.0-SNAPSHOT.jar"]
