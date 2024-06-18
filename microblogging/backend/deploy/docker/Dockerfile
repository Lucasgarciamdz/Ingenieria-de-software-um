FROM amazoncorretto:11

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn package -DskipTests

ENTRYPOINT ["java", "-jar", "target/MicrobloggingApplication.jar"]