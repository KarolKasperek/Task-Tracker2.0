FROM openjdk:20
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/demo-0.0.1-SNAPSHOT.jar"]