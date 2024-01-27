# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/deliveryApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
