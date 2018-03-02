FROM openjdk:8-jdk-alpine
MAINTAINER candres0819@gmail.com
ADD ./target/reservas.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]