# syntax-docker/dockerfile:1
FROM eclipse-temurin:21-jre

# Work dir
WORKDIR /app

# 1. Maven - clean (./nvmw clean)
# 2. Maven - package

# Copy the built Spring Boot jar into the image
COPY target/shopping-cart-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the ost your app listens on
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t dockerlecture .
# docker run -p 8080:8080 dockerlecture

# 3. docker login
# 4. docker build -t danyko/shopping-cart-api .
