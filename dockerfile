# Etapa 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/notification-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
