FROM openjdk:8

WORKDIR /drcontroller-backend

RUN mvn clean package -DskipTests

COPY target/drawingController-0.0.1-SNAPSHOT.jar /drcontroller-backend/drcontroller-backend.jar

ENTRYPOINT ["java","-jar","drcontroller-backend.jar"]