FROM openjdk:17-jdk-alpine
WORKDIR /app
EXPOSE 8080
COPY target/*.jar /app/librarydemo.jar
ENTRYPOINT ["java", "-jar", "/app/librarydemo.jar"]