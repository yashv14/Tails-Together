# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Serve the application using Tomcat 10.1 (Jakarta EE 10 compatible)
FROM tomcat:10.1-jdk17
# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy the built WAR file to Tomcat's webapps directory as ROOT.war to serve it at the root path
COPY --from=build /app/target/pet_manage.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
