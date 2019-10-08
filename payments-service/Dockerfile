FROM openjdk:8
ADD target/payments-service-0.0.1-SNAPSHOT.jar payments-service.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "payments-service.jar"]