FROM maven:3.9.6-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app
COPY --from=build /target/*.jar ./spring-deskcurso.jar
EXPOSE 8080
ENTRYPOINT java -jar spring-deskcurso.jar