FROM eclipse-temurin:17

WORKDIR /app

COPY product-service/target/product-service-0.0.1-SNAPSHOT.jar /app/product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]