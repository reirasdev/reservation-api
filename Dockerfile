FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD target/reservationmicroservice-0.0.1-SNAPSHOT.jar reservationmicroservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/reservationmicroservice-0.0.1-SNAPSHOT.jar"]