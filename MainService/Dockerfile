#define base docker image
FROM openjdk:17
ADD target/MainService-0.0.1-SNAPSHOT.jar MainService.jar
ENTRYPOINT ["java", "-jar", "MainService.jar"]