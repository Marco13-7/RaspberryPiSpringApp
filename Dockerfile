FROM maven:3-jdk-11 as maven

COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build

RUN mvn package

FROM openjdk:11-jre-slim-buster

WORKDIR /app

COPY --from=maven /build/target/ShotBot-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "ShotBot-0.0.1-SNAPSHOT.jar"]