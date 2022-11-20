FROM maven:3.8.6-openjdk-11 AS mvn-build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean install

FROM openjdk:11
COPY --from=mvn-build /app/target/25_docker-0.1.0-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]