FROM maven:3.8.6-openjdk-11 AS mvn-build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn -f /app/pom.xml clean package