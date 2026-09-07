# Multi-stage Dockerfile: build with Maven then run with a slim JRE

FROM maven:3.8.8-openjdk-17-slim AS build
WORKDIR /workspace
# Copy Maven wrapper & settings where present to speed up builds
COPY pom.xml ./
COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
# Copy source and build
COPY src ./src
RUN mvn -B -DskipTests package

# Runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /workspace/target/LocationTrackerServer-0.0.1-SNAPSHOT.jar app.jar
# Persist H2 file-based database here
VOLUME ["/app/data"]
# Expose app port and H2 console/tcp ports
EXPOSE 8081 8082 9092
# Use JAVA_OPTS if you want to pass extra args when running
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=8081 -Dspring.datasource.url=jdbc:h2:file:./data/locationdb -jar /app/app.jar"]
