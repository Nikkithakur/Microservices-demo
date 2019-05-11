FROM openjdk:8
ADD target/eurekaservice-0.0.1-SNAPSHOT.jar eureka-service.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "eureka-service.jar"]