# =============================================================
# Dockerfile — GreetingApp (Spring MVC 6 + Tomcat 11 + Java 21)
# =============================================================
#
# MULTI-STAGE BUILD EXPLAINED:
#
# Stage 1 (builder): Uses Maven to compile and package the WAR.
# Stage 2 (runtime): Uses Tomcat 11 to run the packaged WAR.
#
# WHY multi-stage?
# The final image only contains Tomcat + the WAR file.
# Maven, source code, and build tools are NOT in the final image.
# This keeps the image small and secure (no unnecessary tools).
# =============================================================

# =============================================
# STAGE 1: Build the WAR with Maven
# =============================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

LABEL stage="builder"

# Set working directory inside the container
WORKDIR /build

# Copy the POM first (layer caching: Maven downloads deps only when pom.xml changes)
COPY pom.xml .

# Download all dependencies — cached as a Docker layer
# If pom.xml hasn't changed, this layer is reused on next build
RUN mvn dependency:go-offline -B

# Copy the full source code
COPY src ./src

# Build the WAR file, skipping tests for faster build
# Output: target/GreetingApp.war
RUN mvn clean package -DskipTests -B

# =============================================
# STAGE 2: Run with Apache Tomcat 11 + Java 21
# =============================================
FROM tomcat:11.0-jdk21-temurin-jammy AS runtime

LABEL maintainer="rudresh@example.com"
LABEL description="GreetingApp - Spring MVC 6 + Tomcat 11 + Java 21"
LABEL version="1.0.0"

# Remove Tomcat's default ROOT webapp (we deploy our own)
RUN rm -rf /usr/local/tomcat/webapps/ROOT \
           /usr/local/tomcat/webapps/examples \
           /usr/local/tomcat/webapps/docs \
           /usr/local/tomcat/webapps/host-manager \
           /usr/local/tomcat/webapps/manager

# Copy the WAR from the builder stage into Tomcat's webapps directory.
# Naming it ROOT.war makes the app available at "/" (no context path).
# If you want /GreetingApp context path, name it GreetingApp.war.
COPY --from=builder /build/target/GreetingApp.war /usr/local/tomcat/webapps/ROOT.war

# Create a logs directory
RUN mkdir -p /usr/local/tomcat/logs

# EXPOSE tells Docker which port Tomcat listens on.
# The actual port mapping is done with -p when running the container.
EXPOSE 8080

# Tomcat's default startup command
CMD ["catalina.sh", "run"]
