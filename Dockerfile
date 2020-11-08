
# Build stage
#
FROM maven:3.6.0-jdk-8 AS build
COPY src /drcontroller-backend/src
COPY pom.xml /drcontroller-backend/
RUN mvn -f /drcontroller-backend/pom.xml clean package -DskipTests


FROM openjdk:8

WORKDIR /drcontroller-backend

#RUN mvn clean package -DskipTests

COPY target/drawingController-0.0.1-SNAPSHOT.jar /drcontroller-backend/drcontroller-backend.jar

EXPOSE 8020
ENTRYPOINT ["java","-jar","drcontroller-backend.jar"]