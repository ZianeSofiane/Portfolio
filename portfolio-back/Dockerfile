# Étape 1 : Build de l'application avec Maven
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image légère pour l'exécution avec Java 21
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de l'application Spring Boot
EXPOSE 8080

# Lancer l'application en activant le profil 'prod'
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]