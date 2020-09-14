FROM openjdk:8-jdk-alpine
LABEL maintainer="Ramazan Sakin <ramazansakin63@gmail.com>"

# Add JAR file and run it as entrypoint
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8182