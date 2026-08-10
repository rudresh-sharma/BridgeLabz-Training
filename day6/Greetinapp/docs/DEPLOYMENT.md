# GreetingApp — Complete Deployment Guide

## Table of Contents
1. [Local Development Setup](#1-local-development-setup)
2. [Build & Package](#2-build--package)
3. [Deploy to Tomcat 11 (Local)](#3-deploy-to-tomcat-11-local)
4. [Docker Deployment](#4-docker-deployment)
5. [Render Deployment Steps](#5-render-deployment-steps)
6. [TiDB Cloud Connection Steps](#6-tidb-cloud-connection-steps)
7. [Troubleshooting](#7-troubleshooting)

---

## 1. Local Development Setup

### Prerequisites

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 21+ | https://adoptium.net |
| Maven | 3.9+ | https://maven.apache.org |
| MySQL | 8.0+ | https://dev.mysql.com/downloads |
| Apache Tomcat | 11.0+ | https://tomcat.apache.org |
| IDE | IntelliJ / Eclipse | — |

### Step 1: Create the Database

```sql
-- Run in MySQL client (mysql -u root -p)
CREATE DATABASE IF NOT EXISTS greeting_app_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE greeting_app_db;

CREATE TABLE IF NOT EXISTS users (
    id       BIGINT        AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100)  NOT NULL,
    email    VARCHAR(100)  NOT NULL UNIQUE,
    password VARCHAR(255)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### Step 2: Configure `db.properties`

Edit `src/main/resources/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/greeting_app_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.username=root
db.password=YOUR_MYSQL_PASSWORD
db.driver=com.mysql.cj.jdbc.Driver
```

---

## 2. Build & Package

```bash
# Navigate to the project root
cd GreetingApp

# Clean and package to WAR (skipping tests for speed)
mvn clean package -DskipTests

# The WAR is created at:
# target/GreetingApp.war
```

> [!IMPORTANT]
> `failOnMissingWebXml=false` is set in pom.xml so Maven does not
> fail because there is no web.xml (we use annotation config).

---

## 3. Deploy to Tomcat 11 (Local)

### Option A: Copy WAR manually

```bash
# Copy WAR to Tomcat's webapps directory
cp target/GreetingApp.war /path/to/apache-tomcat-11/webapps/

# Start Tomcat
/path/to/apache-tomcat-11/bin/startup.sh   # Linux/Mac
/path/to/apache-tomcat-11/bin/startup.bat  # Windows
```

Access the app at: **http://localhost:8080/GreetingApp/**

### Option B: IntelliJ IDEA (Recommended for development)

1. Open **Run → Edit Configurations**
2. Click **+** → **Tomcat Server → Local**
3. In **Server** tab → set Tomcat home path
4. In **Deployment** tab → click **+** → **Artifact** → `GreetingApp:war exploded`
5. Set **Application context** to `/GreetingApp`
6. Click **Run** ▶

---

## 4. Docker Deployment

### Build and run with Docker Compose (recommended):

```bash
# Build image and start all services (MySQL + Tomcat)
docker-compose up --build

# Run in background
docker-compose up --build -d

# View logs
docker-compose logs -f app

# Stop
docker-compose down

# Stop and remove database volume
docker-compose down -v
```

Access at: **http://localhost:8080/**

### Build manually:

```bash
# Build the Docker image
docker build -t greetingapp:1.0.0 .

# Run MySQL
docker run -d \
  --name greetingapp-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=greeting_app_db \
  -p 3306:3306 \
  mysql:8.3

# Run the app
docker run -d \
  --name greetingapp \
  --link greetingapp-mysql:db \
  -p 8080:8080 \
  -e JAVA_OPTS="-Ddb.url=jdbc:mysql://db:3306/greeting_app_db?useSSL=false&serverTimezone=UTC -Ddb.username=root -Ddb.password=root" \
  greetingapp:1.0.0
```

---

## 5. Render Deployment Steps

[Render](https://render.com) is a cloud platform that can host Docker applications.

### Step 1: Push to GitHub

```bash
git init
git add .
git commit -m "Initial commit: GreetingApp Spring MVC 6"
git remote add origin https://github.com/YOUR_USERNAME/GreetingApp.git
git push -u origin main
```

### Step 2: Create a Render Web Service

1. Go to [render.com](https://render.com) and **sign in**.
2. Click **New +** → **Web Service**.
3. Connect your **GitHub repository**.
4. Configure:

| Setting | Value |
|---------|-------|
| Name | `greetingapp` |
| Region | `Oregon (US West)` |
| Branch | `main` |
| Runtime | `Docker` |
| Dockerfile Path | `./Dockerfile` |
| HTTP Port | `8080` |

### Step 3: Add Environment Variables on Render

In **Environment → Add Environment Variable**:

| Key | Value |
|-----|-------|
| `JAVA_OPTS` | `-Ddb.url=jdbc:mysql://YOUR_TIDB_HOST:4000/greeting_app_db?useSSL=true&serverTimezone=UTC -Ddb.username=YOUR_USER -Ddb.password=YOUR_PASS -Ddb.driver=com.mysql.cj.jdbc.Driver` |

> [!NOTE]
> Replace `YOUR_TIDB_HOST`, `YOUR_USER`, `YOUR_PASS` with your TiDB Cloud credentials (see Section 6).

### Step 4: Deploy

Click **Create Web Service**. Render will:
1. Clone your repo
2. Build the Docker image
3. Deploy Tomcat with your WAR
4. Give you a public URL like `https://greetingapp.onrender.com`

> [!WARNING]
> Render free tier sleeps after 15 minutes of inactivity. The first
> request after sleep takes ~30 seconds to wake up.

---

## 6. TiDB Cloud Connection Steps

TiDB Cloud is a managed MySQL-compatible database — perfect for free cloud hosting.

### Step 1: Create a TiDB Cluster

1. Go to [tidbcloud.com](https://tidbcloud.com) → **Sign up free**.
2. Click **Create Cluster** → Choose **Serverless** (free tier).
3. Select region → click **Create**.
4. Wait ~30 seconds for cluster to be ready.

### Step 2: Get Connection String

1. Click your cluster → **Connect**.
2. Choose **General** → **Java**.
3. Copy the connection details:

```
Host:     gateway01.us-west-2.prod.aws.tidbcloud.com
Port:     4000
User:     YOUR_TIDB_USER
Password: YOUR_TIDB_PASSWORD
Database: test  (change to greeting_app_db)
```

### Step 3: Create the Database and Table

In TiDB Cloud SQL Editor or via MySQL client:

```sql
CREATE DATABASE IF NOT EXISTS greeting_app_db;
USE greeting_app_db;

CREATE TABLE IF NOT EXISTS users (
    id       BIGINT        AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100)  NOT NULL,
    email    VARCHAR(100)  NOT NULL UNIQUE,
    password VARCHAR(255)  NOT NULL
);
```

### Step 4: Update `db.properties` for TiDB

```properties
# TiDB Cloud connection (SSL required)
db.url=jdbc:mysql://gateway01.us-west-2.prod.aws.tidbcloud.com:4000/greeting_app_db?useSSL=true&serverTimezone=UTC&sslMode=VERIFY_IDENTITY
db.username=YOUR_TIDB_USER.root
db.password=YOUR_TIDB_PASSWORD
db.driver=com.mysql.cj.jdbc.Driver
```

> [!IMPORTANT]
> TiDB Cloud **requires SSL** (`useSSL=true`). The MySQL Connector/J
> 8.3 handles this automatically when `useSSL=true` is set.

### Step 5: For Render + TiDB

Set the `JAVA_OPTS` environment variable on Render with the TiDB connection string:

```
-Ddb.url=jdbc:mysql://gateway01.us-west-2.prod.aws.tidbcloud.com:4000/greeting_app_db?useSSL=true&serverTimezone=UTC
-Ddb.username=YOUR_USER
-Ddb.password=YOUR_PASSWORD
-Ddb.driver=com.mysql.cj.jdbc.Driver
```

> [!TIP]
> To read JAVA_OPTS system properties in `db.properties`, update `WebConfig.java`
> to fall back to system properties:
>
> ```java
> String url = System.getProperty("db.url",
>              env.getProperty("db.url"));
> ds.setUrl(url);
> ```

---

## 7. Troubleshooting

### `ClassNotFoundException: com.mysql.cj.jdbc.Driver`
- MySQL connector JAR is `<scope>runtime</scope>` in pom.xml — it should be in WEB-INF/lib.
- Run `mvn dependency:tree | grep mysql` to verify.

### `404 Not Found` on all pages
- Verify `AppInitializer` extends `AbstractAnnotationConfigDispatcherServletInitializer`.
- Check Tomcat logs for Spring context initialization errors.
- Ensure `failOnMissingWebXml=false` in `maven-war-plugin`.

### JSP not rendering / blank page
- Check `InternalResourceViewResolver` prefix/suffix in `WebConfig`.
- Confirm JSP files are in `src/main/webapp/WEB-INF/views/`.
- JSTL requires `jakarta.servlet.jsp.jstl` on the classpath.

### Thymeleaf `TemplateInputException`
- Confirm templates are in `src/main/webapp/WEB-INF/templates/`.
- `ThymeleafConfig.templateResolver()` prefix must be `/WEB-INF/templates/`.
- Controller must return `"thymeleaf/greeting"` (matching the viewNames pattern).

### Static resources (CSS/JS) 404
- Check `WebConfig.addResourceHandlers()` is configured.
- URL must start with `/resources/` matching `addResourceHandler("/resources/**")`.
- In JSP use `<c:url value='/resources/css/style.css'/>`.

### Database connection refused
- Check MySQL is running: `mysql -u root -p`.
- Verify `db.url` in `db.properties` matches your MySQL host/port.
- For Docker: use `db` hostname (the service name) not `localhost`.
