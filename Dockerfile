# Use a base image with Java and Spark installed
FROM bitnami/spark:2.4.3

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/test-0.0.1-SNAPSHOT.jar /app/test-0.0.1-SNAPSHOT.jar

# Set the entry point command
CMD ["spark-submit", "--class", "org.spark.learning.Demo", "test-0.0.1-SNAPSHOT.jar"]
