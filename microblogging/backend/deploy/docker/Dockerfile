
FROM maven:3.9.7-amazoncorretto-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests

FROM amazoncorretto:21-alpine3.19
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]