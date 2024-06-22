
FROM maven:3.8.4-openjdk-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]