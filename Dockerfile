# Use official OpenJDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and source code
COPY . .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose the port (Spring Boot default is 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/krishimart-0.0.1-SNAPSHOT.jar"]
