FROM maven:3.9.6-amazoncorretto-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/apiusuarios-0.0.1-SNAPSHOT.jar apiusuarios-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","apiusuarios-0.0.1-SNAPSHOT.jar"]