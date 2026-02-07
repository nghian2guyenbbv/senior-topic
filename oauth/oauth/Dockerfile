FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
# Copy pom.xml first for better caching
COPY pom.xml .
RUN mvn dependency:go-offline

#copy source code
COPY src ./src

# build the application
RUN mvn clean package -DskipTests

# Run time
FROM eclipse-temurin:17-jre
WORKDIR /app

#Copy jar file
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]