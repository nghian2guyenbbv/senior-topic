FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
# Copy pom file for caching, download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline
#copy source code
COPY src ./src
#build maven package
RUN mvn clean package -DskipTests

#Run time
FROM eclipse-temurin:17-jre
WORKDIR /app

#Copy jar file 
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "app.jar" ]

