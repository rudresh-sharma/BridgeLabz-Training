# Refresher Training (Daily Progress)

Day-wise log of my BridgeLabz Refresher Training 

**Repo:** [BridgeLabz-Training (Refresher-Training branch)](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training)

---



## 📅 Day 1 — 31 July 2026

Focus: Core SQL — DDL, DML, and related concepts.

| Folder | Topic |
|---|---|
| `01_DDL` | Data Definition Language — `CREATE`, `ALTER`, `DROP` for databases & tables |
| `02_DML` | Data Manipulation Language — `SELECT`, `INSERT`, `UPDATE`, `DELETE` |
| `03_Constraints` | Table/column constraints |
| `04_Keys` | Primary keys, foreign keys |
| `05_Aggregate Functions` | `COUNT`, `SUM`, `AVG`, `MIN`, `MAX` |
| `06_GroupBy_Having` | `GROUP BY` and `HAVING` clauses |
| `07_JOINS` | SQL joins |
| `08_Subqueries` | Nested queries |
| `09_SetOperators` | `UNION`, `INTERSECT`, etc. |
| `10_StringFunction` | String functions |
| `11_Date_Function` | Date/time functions |
| `12_Views` | Creating and using views |
| `13_storeprocedur` | Stored procedures |
| `14_UserDefinedFunction` | User-defined functions |
| `15_Transactions` | Transaction control |
| `16_Trigger` | Triggers |
| `17_Indexing` | Indexing for performance |

📂 [`day1/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day1)

---

## 📅 Day 2 — 3 August 2026

Focus: ER diagrams and applying SQL concepts to build a real database application.

- Learned ER (Entity-Relationship) diagrams — components and what each represents.
- Revised joins, `GROUP BY`/`HAVING`, stored procedures, views, and triggers.
- Built **HealthClinicApp** — a MySQL-based console application implementing everything learned so far.

### 🏥 HealthClinicApp

**Tables:** `Patient`, `Department`, `Doctor`, `Appointment`
(Patient & Doctor primary keys are foreign keys in Appointment; Doctor references Department.)

**Features implemented:**

- **Patient** — search by ID/phone, update, delete, view all, filter by age or join date, find by doctor visited, find patients with no appointments
- **Doctor** — search by ID/specialty, update specialty/fee, delete, view all, list by department, count per department
- **Appointment** — book, cancel, reschedule, complete, view by day (today/tomorrow/date/range), patient/doctor history, count by status
- **Department** — add, update, delete, view all, count doctors per department

📄 [`day2/HEALTHCLINICAPP.sql`](https://github.com/rudresh-sharma/BridgeLabz-Training/blob/Refresher-Training/day2/HEALTHCLINICAPP.sql)
📂 [`day2/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day2)

---

## 📅 Day 3 — 4 August 2026

Focus: Advanced SQL practice and connecting Java to MySQL via JDBC.

| Folder | Topic |
|---|---|
| [`JointsLearning`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/JointsLearning) | Joins practice (`JoinsPracticeDay3.sql`) |
| [`StoreProcedure`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/StoreProcedure) | Stored procedure practice (`StoreProcedureLearnings.sql`) |
| [`Triggers`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/Triggers) | Trigger practice (`triggersprac.sql`) |
| [`MySQLPracticeWithJDBC`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3/MySQLPracticeWithJDBC) | Maven Java project connecting to MySQL using JDBC |

### ☕ MySQLPracticeWithJDBC

A Maven-based Java project (`pom.xml`) that connects to a MySQL database using JDBC, under package `com.mysqlwithjdbc`, with a matching test package.

📂 [`day3/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day3)

---

## 📅 Day 4 — 5 August 2026

Focus: Expanding the HealthClinicApp into a full-scale JDBC + MySQL console application covering every major SQL concept end-to-end.

### 🏥 HealthClinicAppMySQLAndJDBC

A Maven-based Java 17 console application (`com.healthclinicapp`) that connects to MySQL via JDBC. On startup it verifies the DB connection, then runs an idempotent `DatabaseInitializer` (creates the database, tables, indexes, views, functions, triggers, events, and seed data) before launching a menu-driven UI.

**Expanded domain model (DAO + model classes):**
`Patient`, `Doctor`, `Department`, `Appointment`, `Visit`, `Admission`, `Room`, `Billing`, `Payment`, `Insurance`, `Prescription` / `PrescriptionItem`, `Medicine`, `Inventory`, `Supplier`, `LabTest` / `LabReport`, `Disease`, `MedicalHistory`, `EmergencyContact`, `Staff`, `Feedback`, `ActivityLog` / `AuditLog`

**Main Menu:**

| # | Module |
|---|---|
| 1 | Patient Management |
| 2 | Doctor Management |
| 3 | Department Management |
| 4 | Appointment Management |
| 5 | Visit Management |
| 6 | Billing & Payments |
| 7 | Medicine & Inventory |
| 8 | Lab Tests & Reports |
| 9 | Room & Admission Management |
| 10 | Reports & Analytics |
| 11 | SQL Practice Menu |
| 12 | Database Management |

**Architecture:** layered into `dao/` (data access), `menu/` (console UI per module), `model/` (POJOs), `database/` (connection + schema initializer), and `util/` (input, date, color, print, validation helpers), with a matching `src/test` package.

**Docs:** includes an ER diagram (`HealthClinicAPPER.png`) and a full database design write-up (`HealthClinicApp_Database_Design_Document.docx`).

📄 [`day4/HealthClinicAppMySQLAndJDBC/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day4/HealthClinicAppMySQLAndJDBC)
📂 [`day4/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day4)

---


## 📅 Day 5 — 6 August 2026

Focus: Dependency Injection patterns in Java (annotation-based, XML-based, and manual/plain Java) and building a first Servlet + JSP web application with MySQL.

| Folder | Topic |
|---|---|
| [`DependencyInjectionDemonstration/StudentManagementAnnotation`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day5/DependencyInjectionDemonstration/StudentManagementAnnotation) | DI using annotation-based configuration (`AppConfig`) |
| [`DependencyInjectionDemonstration/StudentManagementJava`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day5/DependencyInjectionDemonstration/StudentManagementJava) | DI using plain Java-based configuration |
| [`DependencyInjectionDemonstration/StudentManagementXML`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day5/DependencyInjectionDemonstration/StudentManagementXML) | DI using XML-based configuration (`beans.xml`) |
| [`FirstServletProject`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day5/FirstServletProject) | Servlet + JSP web app with MySQL (login/signup/dashboard) |

### 💉 DependencyInjectionDemonstration

Three parallel Maven projects implementing the same **Student Management** domain (`Student`, `StudentDAO` / `StudentDAOImpl`, `StudentService`), each wiring dependencies a different way to compare DI styles side by side:

- **StudentManagementAnnotation** — dependencies wired via `AppConfig` using annotations, with separate `StudentServiceConstructor` / `StudentServiceField` implementations to demonstrate constructor vs. field injection.
- **StudentManagementJava** — same domain, configured via a plain Java-based config class (no annotations, no XML).
- **StudentManagementXML** — same domain, wired declaratively via `beans.xml`.

Common structure across all three: `config/`, `dao/`, `model/`, `service/`, `util/` (with `DBConnection`), plus a matching `src/test` package.

### 🌐 FirstServletProject

A Maven-based Java web app using Servlets and JSP, backed by MySQL.

**Structure:**
- `controller/` — `LoginServlet`, `LogoutServlet`, `SignupServlet`
- `dao/` — `UserDAO`
- `model/` — `User`
- `util/` — `DBConnection`
- `webapp/` — `index.jsp`, `login.jsp`, `signup.jsp`, `dashboard.jsp`, and `WEB-INF/web.xml`

Uses `mysql-connector-j` for database connectivity and builds to a deployable `.war`.

📂 [`day5/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day5)



## 📅 Day 6 — 7 August 2026

Focus: Building a production-style Spring MVC 6 web app from scratch (dual view technology, plain JDBC, MySQL) and containerizing + deploying it live with Docker and Render.

### 🌱 GreetingApp

A Maven-based **Spring MVC 6 + Java 21** web application deployed to **Apache Tomcat 11**, demonstrating annotation-based Spring configuration, dual view rendering (JSP *and* Thymeleaf), plain JDBC against MySQL, and a full Docker-based deployment pipeline.

**Structure (`com.springmvc`):**
- `config/` — Spring Java-based configuration (`WebConfig`, `AppInitializer`, `ThymeleafConfig`)
- `controller/` — request handlers for the greeting flow
- `dao/` — plain JDBC data access layer
- `model/` — POJOs
- `service/` — business logic layer
- `util/` — `DBConnection` and helpers
- `webapp/WEB-INF/` — JSP views + static resources
- `resources/` — `db.properties`, `logback.xml`

**Key features:**
- Dual view resolution — JSP (`InternalResourceViewResolver`) and Thymeleaf (`ThymeleafViewResolver`) side by side in the same app
- Annotation-driven config — no `web.xml`, wired via `AbstractAnnotationConfigDispatcherServletInitializer`
- Plain JDBC + MySQL 8 (`mysql-connector-j`) for persistence, with SLF4J + Logback logging
- Packaged as a WAR (`GreetingApp.war`) via `maven-war-plugin`

**🐳 Containerized & deployed:**
- Multi-stage `Dockerfile` — Stage 1 builds the WAR with Maven (`maven:3.9.6-eclipse-temurin-21-alpine`), Stage 2 runs it on `tomcat:11.0-jdk21-temurin-jammy`, keeping the final image lean (no build tools baked in)
- `docker-compose.yml` — spins up the app alongside a MySQL 8 container with a healthcheck-gated startup and schema auto-init
- Deployed live on **Render** (Docker runtime) connected to a **TiDB Cloud** MySQL-compatible database
- Full deployment walkthrough documented in [`DEPLOYMENT.md`](https://github.com/rudresh-sharma/BridgeLabz-Training/blob/Refresher-Training/day6/Greetinapp/DEPLOYMENT.md) — local setup, Docker, Render, and TiDB Cloud connection steps

**🔗 Live:** [greetingapp-63a4.onrender.com](https://greetingapp-63a4.onrender.com)

📄 [`day6/Greetinapp/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day6/Greetinapp)
📂 [`day6/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day6)

---



## 📅 Day 7 — 10 August 2026

Focus: Building a layered Spring Boot 4 REST API from scratch with JPA, Bean Validation, global exception handling, and OpenAPI/Swagger docs.

### 📇 MY-CONTACT-APP

A Maven-based **Spring Boot 4.1 + Java 21** REST API for managing contacts, backed by **Spring Data JPA** with an **H2** in-memory database (console enabled), and documented via **springdoc-openapi** (Swagger UI).

**Structure (`com.mycontactapp`):**
- `controller/` — `ContactController` (REST endpoints + controller-level exception handling)
- `dto/` — `ContactRequestDTO`, `ContactResponseDTO`, `ValidationErrorDTO`
- `entity/` — `Contact` (JPA entity, unique email constraint)
- `exception/` — `ContactNotFoundException`, `EmailAlreadyExistsException`, `GlobalExceptionHandler`
- `mapper/` — `ContactMapper` (DTO ⇄ entity conversion)
- `repository/` — `ContactRepository` (Spring Data JPA, `existsByEmail`)
- `service/` — `ContactService` / `ContactServiceImpl`
- `resources/` — `application.yaml` (H2 datasource + console config)

**Key features:**
- Full CRUD REST API — create, get by ID, get all, update, delete contacts under `/api/contacts`
- **Bean Validation** on requests — `@NotBlank`, `@Email`, `@Size`, `@Pattern` (10-digit phone/alternate-phone validation), with validation errors mapped to a clean `List<ValidationErrorDTO>` response
- **Global exception handling** via `@RestControllerAdvice` — `MethodArgumentNotValidException` → `400`, unexpected errors → `500`, plus a controller-level `ContactNotFoundException` → `404` handler
- **Business rule enforcement** — duplicate email check (`EmailAlreadyExistsException`) before create
- **Phone normalization** — strips leading zeros from phone/alternate phone before persisting
- **Layered architecture** — clean separation across controller → service → mapper → repository, DTOs used at the API boundary instead of exposing the entity directly
- **API docs** — `springdoc-openapi-starter-webmvc-ui` auto-generates Swagger UI with `@Tag`, `@Operation`, and `@ApiResponses` annotations on every endpoint
- **H2 Console** enabled at `/h2-console` for quick in-memory DB inspection during development
- Lombok (`@Getter`/`@Setter`/`@NoArgsConstructor`/`@AllArgsConstructor`) to keep entity/DTOs concise

📂 [`day7/MY-CONTACT-APP/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day7/MY-CONTACT-APP)

---

## 📅 Day 8 — 11 August 2026
 
Focus: Unit testing the Spring Boot REST API from Day 7 — controller-layer testing with MockMvc/Mockito and service-layer testing with Mockito/AssertJ.
 
### 📇 MY-CONTACT-APP (continued)
 
Added a full **JUnit 5** test suite (24 tests) on top of the Day 7 `MY-CONTACT-APP`, covering both the web layer and the business logic layer in isolation.
 
**New test classes (`src/test/java/com/mycontactapp`):**
- `ContactControllerTest` — 12 tests. Builds `MockMvc` in **standalone mode** with `ContactController` + `GlobalExceptionHandler` registered, so validation errors and service-thrown exceptions resolve exactly as they would at runtime. Covers all five endpoints (`POST`, `GET /{id}`, `GET`, `PUT`, `DELETE`) for both happy paths and failure paths (`400` validation errors, `404` not found, `409` duplicate email).
- `ContactServiceImplTest` — 12 tests. Mocks `ContactRepository` and `ContactMapper` via Mockito (`@Mock`/`@InjectMocks`) to test `ContactServiceImpl` business logic in isolation — email-uniqueness enforcement, phone/alternate-phone leading-zero normalization, not-found handling, null-safe alternate-phone handling, and correct delegation to the mapper/repository, verified with `ArgumentCaptor`.
**Key testing practices demonstrated:**
- **Mockito** (`@Mock`, `@InjectMocks`, `@ExtendWith(MockitoExtension.class)`) to isolate each layer under test
- **MockMvc** standalone setup for controller tests — no full Spring context load, faster and more focused
- **AssertJ** (`assertThat`, `assertThatThrownBy`) for fluent, readable assertions
- **`ArgumentCaptor`** to verify the exact object passed to the repository (e.g. confirming phone numbers are normalized before save)
- **`@DisplayName`** on every test for human-readable test reports
- Both success and failure paths tested for every CRUD operation — not just the happy path
📂 [`day8/MY-CONTACT-APP/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day8/MY-CONTACT-APP)
 
---


## 📅 Day 9 — 12 August 2026

Focus: Extending the Day 7/8 `MY-CONTACT-APP` with a "Favourites" feature — new entity field, repository query, endpoints, and updated test coverage.

### 📇 MY-CONTACT-APP (continued)

Added an **`isFavourite`** flag to the `Contact` entity, with service/controller logic and endpoints to mark, unmark, and list favourite contacts.

**Changes (`com.mycontactapp`):**
- `entity/Contact` — new `isFavourite` field (`Boolean`, defaults `FALSE`)
- `repository/ContactRepository` — new derived query `findByIsFavouriteTrue()`
- `service` — new methods `madeAFavourite(id)`, `removeAFavourite(id)`, `getAllFavourites()`
- `controller` — three new endpoints; `dto`/`mapper` updated to carry `isFavourite`

**New endpoints:**
- `PATCH /api/contacts/{id}/favourite` — mark as favourite
- `DELETE /api/contacts/{id}/favourite` — remove from favourites
- `GET /api/contacts/favourites` — list all favourites

**Notes:**
- Reuses the existing `ContactNotFoundException` → `404` pattern for invalid IDs
- `ContactControllerTest` / `ContactServiceImplTest` updated to cover the new field and behavior

📂 [`day9/MY-CONTACT-APP/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day9/MY-CONTACT-APP)

---

## 📅 Day 10 — 13 August 2026

Focus: New **Employee Payroll** Spring Boot 4 REST API with Spring Data JDBC, Flyway migrations, and the Strategy pattern for salary calculation — plus `MY-CONTACT-APP` carried forward.

### 💰 Employee-Pay-Role

Spring Boot 4.1 + Java 21 REST API (`com.employeepayroll`) for `Employee`/`Department`, using **Spring Data JDBC** (not JPA) + H2, schema/data via **Flyway**, and Swagger docs.

**Key features:**
- `Employee`/`Department` implement `Persistable<UUID>` with `@PersistenceCreator` for correct insert-vs-update behavior
- **Strategy pattern**: `SalaryCalculator` → `StandardSalaryCalculator` (12× salary, `@Primary`) and `BonusSalaryCalculator` (12× salary + 10%), chosen via `@Qualifier` + `bonus` flag
- Full CRUD on `/api/employees` & `/api/departments`, plus lookup by email/name
- `GET /api/employees/email/{email}/annual-salary?bonus=true|false`
- Pagination, `@Valid` validation, `GlobalExceptionHandler`, H2 console, DevTools

### 📇 MY-CONTACT-APP (carried forward)

`MY-CONTACT-APP` Added Search by firstName feature.


📂 [`day10/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day10)

---
## 📅 Day 11 — 14 August 2026
 
Focus: Migrating the Day 10 **Employee-Pay-Role** API from Spring Data JDBC to full **Spring Data JPA/Hibernate**, with real entity relationships, a custom JPQL query, and new endpoints.
 
### 💰 Employee-Pay-Role (JDBC → JPA migration)
 
**Entities (`com.employeepayroll.entity`):**
- `Employee` / `Department` — dropped `Persistable<UUID>` + `@PersistenceCreator` (the Spring Data JDBC pattern) in favor of standard `@Entity` / `@Table` / `@Id` with Hibernate's `@UuidGenerator`, plus `@NoArgsConstructor`/`@AllArgsConstructor`
- `Employee.department` is now a real `@ManyToOne(fetch = LAZY)` + `@JoinColumn("department_id")` relationship instead of a raw `departmentId` UUID field
**Repository (`EmployeeRepository`, `DepartmentRepository`):**
- Both now extend `JpaRepository<T, UUID>` instead of `ListCrudRepository`/`PagingAndSortingRepository`
- New custom JPQL query: `findHighEarners(minSalary)` — `SELECT e FROM Employee e WHERE e.salary > :minSalary ORDER BY e.salary DESC`
**New endpoints (`EmployeeController`):**
- `GET /api/employees/high-earners?minSalary=` — employees earning above a threshold, sorted by salary descending
- `GET /api/employees/totalEmployee` — total employee count (`repository.count()`)
**Database:**
- `pom.xml` swapped `spring-boot-starter-data-jdbc` (+ its test starter) for `spring-boot-starter-data-jpa` (+ test starter)
- `application.yaml` — added `DATABASE_TO_UPPER=FALSE` to the H2 JDBC URL so Hibernate's lower-case, quoted identifiers match the schema
- New Flyway migration `V4__fix_department_case.sql` — drops and re-adds the `employee → department` foreign key while renaming both tables and all their columns to quoted lower-case identifiers (`"department"`, `"employee"`, `"id"`, `"name"`, etc.), aligning the H2 schema with Hibernate's default naming so JPA can resolve the mapping correctly
📂 [`day-11/Employee-Pay-Role/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day-11/Employee-Pay-Role)
📂 [`day-11/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day-11)
 
---



## 📅 Day 12 — 17 August 2026
 
Focus: Cross-cutting concerns on the Day 11 **Employee-Pay-Role** JPA API — AOP-based audit logging and method logging, a full JUnit 5 test suite, structured Logback configuration, and small entity/repository fixes.
 
### 💰 Employee-Pay-Role (AOP + testing + logging)
 
**New: `audit/` package — AOP-based audit trail**
- `AuditAspect` — `@Aspect` bean using `@AfterReturning` to intercept `create`/`update`/`delete` methods on both `EmployeeServiceImpl` and `DepartmentServiceImpl`; on success it writes an `AuditLog` row (`action`, `entityType`, `entityId` extracted via reflection off the returned DTO's `getId()`, `details`, `timestamp`)
- `AuditLog` — new `@Entity` (`audit_log` table) with `@UuidGenerator`-backed `id`
- `AuditLogRepository` — plain `JpaRepository<AuditLog, UUID>`
- New Flyway migration `V5__create_audit_log_table.sql` — creates the `audit_log` table (`id`, `action`, `entity_type`, `entity_id`, `details`, `timestamp`)
**New: `logging/LoggingAspect`**
- `@Aspect` covering `service..*ServiceImpl.*(..)` and `controller..*.*(..)` pointcuts:
  - `@Before` — logs method entry + arguments at `DEBUG`
  - `@AfterReturning` — logs the return value at `DEBUG`
  - `@AfterThrowing` — logs exceptions at `ERROR` regardless of the package log level
  - `@Around` — times every service-layer call and logs duration at `INFO`


**New: `logback-spring.xml`**
- `CONSOLE` appender plus size/time-based `RollingFileAppender`s for all logs (`logs/employee-payroll.log`) and an `ERROR`-only file (`logs/employee-payroll-error.log`), both rolled daily/10 MB with history caps
- `com.employeepayroll` logger set to `INFO` (switch to `DEBUG` to see full `LoggingAspect` entry/exit tracing)
- Noisy frameworks (Spring, Hibernate, Tomcat, Flyway, HikariCP, DevTools) turned down to `WARN`; the Spring Boot condition-evaluation report logger silenced entirely
**New: full JUnit 5 test suite (51 tests)**
- `EmployeeServiceImplTest` (21), `DepartmentServiceImplTest` (12) — Mockito-based service-layer tests
- `EmployeeControllerTest` (11), `DepartmentControllerTest` (7) — `MockMvc` standalone controller tests
- Only `EmployeePayRoleApplicationTests` (context-load smoke test) existed as of Day 11; this is the first real test coverage for the project


**Fixes carried over from Day 11:**
- `Employee` / `Department` entities — dropped the redundant `@AllArgsConstructor` (Lombok already generates one via the combination of `@NoArgsConstructor` + field-level annotations conflicting with JPA proxying)
- `DepartmentRepository.findByName` → `findByNameIgnoreCase`, with `DepartmentServiceImpl` updated to match
- `V4__fix_department_case.sql` simplified — since `DATABASE_TO_UPPER=FALSE` already stores Day 11's unquoted identifiers in lowercase, the migration now only re-establishes the `employee → department` foreign key with quoted names instead of renaming every table/column
📂 [`day12/Employee-Pay-Role/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day12/Employee-Pay-Role)
📂 [`day12/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day12)
 
---


## 📅 Day 13 — 18 August 2026

Focus: A brand-new **Fundo-Notes-App** Spring Boot project — starting the note-taking app's foundation with user registration/login, Spring Security + BCrypt password hashing, Flyway-managed schema, and centralized validation/exception handling. Unlike Day 12 (which built *on top of* the Day 11 **Employee-Pay-Role** API), Day 13 starts a fresh codebase from scratch.

### 📝 Fundo-Notes-App

Spring Boot 4.1 + Java 21 REST API (`com.fundoonotesapp`), using **Spring Data JPA** + MySQL, schema via **Flyway**, `spring-boot-starter-security` for password hashing, and `spring-boot-starter-validation` for request validation — the same core stack pattern as Day 11/12's **Employee-Pay-Role**, but applied to a new `users`/auth domain instead of employees/departments.

**Domain (`user/`):**
- `User` — `@Entity` (`users` table) with `id`, `name`, `email` (unique, not null), `password`, and `provider` (`@Enumerated(STRING)`)
- `AuthProvider` — enum (`LOCAL`, `GOOGLE`) laying groundwork for future OAuth login alongside local email/password accounts
- `UserRepository` — `JpaRepository<User, Long>` with derived queries `findByEmail` and `existsByEmail`

**Auth (`auth/`):**
- `AuthController` — `POST /auth/register`, `POST /auth/login`, `POST /auth/forgot-password`, `POST /auth/reset-password`, all `@Valid @RequestBody`
- `AuthService` — registration (checks `existsByEmail`, hashes password via `PasswordEncoder`, defaults `provider = LOCAL`), login (looks up by email, verifies hash with `passwordEncoder.matches`), forgot-password (validates the email exists; token generation/email sending left as a `// Later:` stub), reset-password (re-hashes and saves a new password)
- DTOs — `RegisterRequest`, `LoginRequest`, `ForgotPasswordRequest`, `ResetPasswordRequest`, `AuthResponse`, each with Jakarta Bean Validation: `@Email`/`@NotBlank` on emails, and a shared `@Pattern` regex requiring 8+ characters with at least one uppercase, one lowercase, one digit, and one special symbol for passwords

**Config & security:**
- `PasswordConfig` — exposes a `BCryptPasswordEncoder` bean
- `SecurityConfig` — `@EnableWebSecurity` filter chain with CSRF disabled, `/auth/**` permitted to all, everything else requiring authentication

**Error handling (`exception/`, `common/dto/`):**
- Package-scoped custom exceptions — `UserAlreadyExistsException` (`user/`), `InvalidCredentialsException` (`auth/`), `ResourceNotFoundException` (`common/`)
- `GlobalExceptionHandler` — `@RestControllerAdvice` mapping each to the right HTTP status (`409` conflict, `401` unauthorized, `404` not found) via `AuthResponse`, plus a `MethodArgumentNotValidException` handler that collects field-level Bean Validation errors into a `ValidationErrorResponse` (`400`)

**Database:**
- New Flyway migration `V1__create_users_table.sql` — creates the `users` table (`id`, `name`, `email` unique, `password`, `provider`), the first migration of this new project (compare to Day 11/12's `Employee-Pay-Role`, which was already up to `V5`)
- `application.properties` (not `.yaml`, unlike `Employee-Pay-Role`) — MySQL datasource pointing at `fundo_notes_db`, `spring.jpa.hibernate.ddl-auto=update`, SQL logging enabled


📂 [`day13/Fundo-Notes-App/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day13/Fundo-Notes-App)
📂 [`day13/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day13)

---

## 📅 Day 14 — 19 August 2026
 
Focus: Adding a **Notes** module and **JWT authentication** (+ account lockout) to the Day 13 `Fundo-Notes-App`.
 
### 📝 Fundo-Notes-App (continued from Day 13)
 
Added `io.jsonwebtoken` (jjwt 0.12.6) to the existing Spring Boot 4.1 / JPA / MySQL / Flyway stack.
 
**New: `notes/` — first real feature**

- `Note` entity (`pinned`/`archived`/`trashed`, timestamps, owning `user`) + `NoteRepository`, `NoteService`, `NoteController` (`POST/GET/PUT/DELETE /notes`), `NoteMapper`
- Migration `V2__create_notes_table.sql`

**New: JWT auth, replacing the plain login check**

- `auth/jwt/JwtService` + `JwtAuthenticationFilter` — issue/validate tokens, authenticate requests via `Authorization: Bearer`
- `security/CustomUserDetails(Service)` — adapts `User` to Spring Security
- `SecurityConfig` moved `security/` → `config/`, rebuilt as stateless (JWT filter + `AuthenticationManager`), absorbing the old `PasswordConfig`
- `AuthService.login` now authenticates via `AuthenticationManager`; `register`/`login` return a JWT in `AuthResponse`
- `TestController` — `GET /test` sanity endpoint for the JWT filter chain

**New: account lockout**
- `User` gains `failedAttempts` / `accountLockedUntil`; 5 failed logins → 15-min lock (`AuthService.login`)
- New `UnauthorizedException` for invalid-credentials/locked cases
- Migration `V3__add_login_attempt_fields.sql`

**Config:** new `.env.example` (`JWT_SECRET`, `JWT_EXPIRATION`) loaded via `spring.config.import` in `application.properties`
 
📂 [`day14/Fundo-Notes-App/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day14/Fundo-Notes-App)
↩️ Previous: [Day 13](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day13/Fundo-Notes-App)
 
---


## 📅 Day 15 — 20 August 2026
 
Focus: Rounding out the Day 14 `Fundo-Notes-App` with **labels**, richer **note lifecycle states** (pin/archive/trash as a single status instead of three booleans), a real **password-reset flow**, **Elasticsearch-backed search**, and **Swagger/OpenAPI** docs.
 
### 📝 Fundo-Notes-App (continued from Day 14)
 
Added `springdoc-openapi-starter-webmvc-ui` (3.0.1) and `spring-boot-starter-data-elasticsearch` to the existing Spring Boot 4.1 / JPA / MySQL / Flyway / JWT stack.
 
**New: `labels/` — first real feature since notes**

- `Label` entity (`name`, owning `user`, unique per `(name, user_id)`) + `LabelRepository`, `LabelService`, `LabelController` (`POST/GET/PUT/DELETE /labels`)
- `Note` ↔ `Label` many-to-many via a `note_labels` join table; `NoteController` gains `POST/DELETE /notes/{noteId}/labels/{labelId}`
- Migrations `V6__create_labels_table.sql`, `V7__create_note_labels_table.sql`

**Changed: note lifecycle — booleans replaced with a status enum**

- `Note.pinned`/`archived`/`trashed` (Day 14 booleans) collapsed into a single `Note.NoteStatus` enum (`ACTIVE`, `PINNED`, `ARCHIVED`, `TRASHED`)
- `NoteController` gains `PATCH /notes/{noteId}/{pin,unpin,archive,unarchive,trash,restore}`, all routed through a shared `NoteService.changeNoteStatus`
- Migration `V5__replace_note_booleans_with_status.sql` — adds `status`, backfills it from the old booleans (trashed > archived > pinned > active), then drops the three boolean columns

**New: real password reset, replacing the Day 13 `// Later:` stub**


- `PasswordResetToken` entity (UUID token, 15-min expiry, one-per-user) + `PasswordResetTokenRepository`
- `AuthService.forgotPassword` now issues and stores a token (returned directly for Postman testing, in place of the old email-sending stub); `resetPassword` validates/expires the token, re-hashes the password, and deletes the token so it can't be reused
- Migration `V4__create_password_reset_tokens_table.sql`

**New: `search/` — Elasticsearch-backed note search**

- `NoteDocument` (`@Document(indexName = "notes")`) + `NoteSearchRepository`, `SearchService`, `SearchController` (`GET /search/notes`) — keyword + status filtering, paginated and sortable (`SearchSortField`, `Sort.Direction`)
- `NoteService` now indexes/updates/deletes the Elasticsearch document alongside every MySQL write (create, update, delete, and every status change)
- `application.properties` gains `spring.elasticsearch.uris`


**New: API docs**


- `OpenApiConfig` — `springdoc` `OpenAPI` bean (title, contact, license, local server)
- `SecurityConfig` permits `/swagger-ui/**` and `/v3/api-docs/**` alongside `/auth/**`; `PasswordConfig`'s `BCryptPasswordEncoder` is now injected into `SecurityConfig` rather than declared inline
- Day 14's `TestController` (`GET /test`) removed — no longer needed now that JWT auth is exercised by real endpoints


📂 [`day15/Fundo-Notes-App/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day15/Fundo-Notes-App)
↩️ Previous: [Day 14](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day14/Fundo-Notes-App)
 
---


## 📅 Day 16 — 21 August 2026
 
Focus: Package-by-feature refactor + Redis caching, RabbitMQ event-driven audit logging, and a JMS/email reminder pipeline for `Fundo-Notes-App`.
 
### 📝 Fundo-Notes-App
 
- **Repackaged** from one flat package into feature packages: `auth/`, `notes/`, `labels/`, `search/`, `security/`, `config/`, `exception/`, `mapper/`, `audit/`, `messaging/`, `notification/`, `reminder/`
- **Redis caching** — `RedisConfig`, `TokenCacheService` (caches JWTs), `UserCacheService` + `CachedUserDetails` (caches `UserDetails`)
- **RabbitMQ event bus** — `RabbitMQConfig` (`note.exchange` + created/updated/deleted routing keys); `NoteEventProducer` publishes on every note write; consumers re-index Elasticsearch and write to a new `audit_logs` table (migration `V9`)
- **Reminders** — new `Reminder` entity/CRUD (`ReminderController`, `ReminderService`, migration `V8`) + `ReminderScheduler` polling every minute for due reminders
- **Notifications** — ActiveMQ Artemis (JMS) queue delivers due reminders to `NotificationListener`, which emails via `EmailService`/`JavaMailSender` and marks the reminder notified
- **Config** — `application.properties` switched to `${ENV_VAR:default}` placeholders; added Redis, Artemis, Mail, RabbitMQ settings
- **pom.xml** — added `spring-boot-starter-data-redis`, `spring-boot-starter-artemis`, `artemis-jakarta-server`, `spring-boot-starter-mail`, `spring-boot-starter-amqp`


📂 [`day16/Fundo-Notes-App/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day16/Fundo-Notes-App)
↩️ Previous: [Day 15](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day15/Fundo-Notes-App)
 

 ---


## 📅 Day 17 —  24 August 2026

Focus: Closing gaps left in Day 16 — fixing invalid `pom.xml` dependency coordinates, completing the RabbitMQ event pipeline so update/delete events actually publish, splitting the shared search queue per event type, and adding a registration welcome email.

### 📝 Fundo-Notes-App (continued from Day 16)

**Fixed: `pom.xml` — several Day 16 starters weren't real Maven coordinates**

- `spring-boot-starter-webmvc` → `spring-boot-starter-web`, and the `-test`-suffixed starters (`spring-boot-starter-data-jpa-test`, `-flyway-test`, `-security-test`, `-validation-test`, `-webmvc-test`) → the single real `spring-boot-starter-test`
- Removed the non-existent `spring-boot-starter-flyway` (the real `flyway-mysql` dependency was already present separately)
- Added `spring-boot-starter-batch`, `poi-ooxml` (5.2.5), and `spring-boot-starter-aspectj` — dependencies the Day 16 `batch/` (CSV/Excel export) and `ControllerLoggingAspect` code needed but never declared

**Fixed: RabbitMQ update/delete events now actually publish**

- `NoteEventProducer` gains `publishNoteUpdated`/`publishNoteDeleted` (Day 16 only had `publishNoteCreated`); `NoteService.updateNote`/`deleteNote` now call these instead of calling `rabbitTemplate.convertAndSend` inline
- The Day 16 `NoteUpdatedAuditConsumer`/`NoteDeletedAuditConsumer`/`*SearchConsumer` listeners were already wired to these routing keys but never received anything, since nothing published to them

**Changed: search queue split per event type**

- The single shared `note.search.queue` (bound to all three routing keys and drained by a generic `NoteSearchConsumer`) is replaced with three dedicated queues — `note.created.search.queue`, `note.updated.search.queue`, `note.deleted.search.queue` — each bound to one routing key
- `NoteSearchConsumer.java` removed; the existing per-event consumers (`NoteCreatedSearchConsumer`, `NoteUpdatedSearchConsumer`, `NoteDeletedSearchConsumer`) each now own their own queue instead of overlapping on one

**New: registration welcome email**

- `AuthService.register` now sends a `RegisterMail` via `NotificationProducer.sendRegisterMail` → new JMS queue `register.email.queue` → `NotificationListener.sendRegisterEmail` → `EmailService.sendRegistrationEmail`, which sends an HTML welcome email (`MimeMessageHelper`) instead of the reminder path's plain-text `SimpleMailMessage`
- `ReminderMessage`/`ReminderScheduler`/`EmailService.sendReminderEmail` now also pass the user's name into the reminder email body

**Cleanup: dropped redundant Redis cache bean**

- Removed `RedisConfig.userRedisTemplate` and the `CachedUser` class — `CachedUserDetails` + `UserCacheService` (already in Day 16) already cover user caching, making this a duplicate

**Fixed: Spring Batch chunk builder for the current Batch version**

- `BatchConfig` — `.chunk(5, transactionManager)` → `.chunk(5).transactionManager(transactionManager)` (separate builder calls)

**Config**

- `application.properties` gains file-based logging (`logging.file.name`, per-package levels, custom console/file patterns), `spring.batch.job.enabled=false` (so the batch job doesn't auto-run on every startup), and `debug` is now hardcoded `false` instead of `${DEBUG:true}`

📂 [`day17/Fundo-Notes-App/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day17/Fundo-Notes-App)
↩️ Previous: [Day 16](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day16/Fundo-Notes-App)

---


## 📅 Day 18 — 25 August 2026
 
Focus: Splitting `Fundo-Notes-App` (Day 17) from a single Spring Boot monolith into a Spring Cloud microservices architecture — `discovery-server`, `api-gateway`, `auth-service`, `user-service`, and `notes-service` — each with its own database, migrations, and deployable unit.
 
### 🧩 FundoNotesMicroservices (replaces Fundo-Notes-App)
 
The Day 1–17 monolith is retired in favor of five independent Maven modules under `FundoNotesMicroservices/`, moving from a package-by-feature structure to service-per-feature. Redis caching, RabbitMQ eventing, Elasticsearch indexing, and the reminder/notification pipeline built up through Day 16–17 are dropped from this scope — Day 18 focuses purely on getting the auth/user/notes split working end-to-end behind service discovery and a gateway.
 
### 🔭 discovery-server
 
- Netflix Eureka server (`spring-cloud-starter-netflix-eureka-server`) on port `8761`
- `register-with-eureka=false` / `fetch-registry=false` — it only hosts the registry, doesn't register itself
📂 [`day18/FundoNotesMicroservices/discovery-server/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices/discovery-server)
 
### 🚪 api-gateway
 
- Spring Cloud Gateway (WebMVC variant, `spring-cloud-starter-gateway-server-webmvc`) on port `8080`, routing by path prefix to each downstream service via Eureka (`lb://AUTH-SERVICE`, `lb://USER-SERVICE`, `lb://NOTES-SERVICE`)
- Routes: `/auth/**` → auth-service, `/users/**` → user-service, `/notes/**` → notes-service, each with a `Retry` filter (3 attempts on `SERVICE_UNAVAILABLE`, `GET`/`POST`)
- `loadbalancer.cache.enabled=false` and a short Eureka fetch/lease interval (5s) so routing picks up newly (de)registered instances quickly
📂 [`day18/FundoNotesMicroservices/api-gateway/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices/api-gateway)
 
### 🔐 auth-service
 
- Owns login/register/refresh/logout only — no user data of its own; calls `user-service` over Feign (`UserClient`) to create/fetch users
- `AuthService.register` encodes the password and delegates user creation to `UserClient.createUser`; `login` fetches credentials via `getUserForAuth`, verifies the password, then issues a JWT (`JwtService`) plus a rotating refresh token
- `RefreshToken` is the only entity owned by this service, backed by its own `refresh_tokens` table (migration `V1`) and `RefreshTokenService` (create/verify/rotate/revoke)
- `SecurityConfig` permits `/auth/register|login|refresh|logout`, requires `ADMIN`/`USER` roles for `/admin/**` and `/user/**`, and authenticates everything else via `JwtAuthenticationFilter`
- Registers with Eureka as `auth-service`
📂 [`day18/FundoNotesMicroservices/auth-service/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices/auth-service)
 
### 👤 user-service
 
- Owns the `User` entity (`id`, `name`, `email`, `password`, `AuthProvider`, `Role`) and its own `users` table (migration `V1`)
- `UserController` exposes CRUD by id/email plus two auth-only lookups (`/users/auth/{userId}`, `/users/auth/email/{email}`) that return `UserAuthResponse` (includes the password hash) for `auth-service`'s Feign calls — the plain `/users/**` responses (`UserResponse`) never expose the password
- No Spring Security dependency — this service isn't meant to be called directly by clients, only through the gateway/other services
- Registers with Eureka as `user-service`
📂 [`day18/FundoNotesMicroservices/user-service/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices/user-service)
 
### 📝 notes-service
 
- Owns the `Note` entity and table (migration `V1`), then migration `V2` swaps the original `user_id` foreign key for a plain `user_email` column + index — notes are now scoped by the email carried in the JWT rather than a cross-service user id lookup
- Its own `JwtService`/`JwtAuthenticationFilter` validate the token locally (shared `jwt.secret`) and set the caller's email as a request attribute, which `NoteController` reads for every operation — no call back to auth-service or user-service per request
- `NoteController`: create, list mine, get by id, update, and pin/unpin (`PATCH /notes/{noteId}/pin`) — the labels, search, reminders, and audit-log features from Day 15–16 are not part of this service
- Registers with Eureka as `notes-service`
📂 [`day18/FundoNotesMicroservices/notes-service/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices/notes-service)
 
### ⬆️ Platform bump
 
- All five modules move from `spring-boot-starter-parent` 4.1.0 (Day 17) to 4.1.1, and correctly use `spring-boot-starter-webmvc` (the Spring Boot 4 name for the servlet-stack web starter) rather than Day 16's invalid `spring-boot-starter-webmvc` coordinate on Boot 3
📂 [`day18/FundoNotesMicroservices/`](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day18/FundoNotesMicroservices)
↩️ Previous: [Day 17](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/Refresher-Training/day17/Fundo-Notes-App)
 
---
## 🛠️ Tech Stack

- **MySQL** — database design & querying
- **Java + JDBC** — database connectivity
- **Maven** — project/dependency management

## 📌 How to Use

1. Clone the repo and switch to the `Refresher-Training` branch:
   ```bash
   git clone https://github.com/rudresh-sharma/BridgeLabz-Training.git
   cd BridgeLabz-Training
   git checkout Refresher-Training
   ```
2. Browse `day1/`, `day2/`, `day3/`, `day4/` for SQL scripts and code from that day.
3. For `MySQLPracticeWithJDBC` (Day 3), update your DB credentials and run with Maven:
   ```bash
   cd day3/MySQLPracticeWithJDBC
   mvn clean install
   ```
4. For `HealthClinicAppMySQLAndJDBC` (Day 4), update your DB credentials in `DatabaseConnection.java` and run with Maven:
   ```bash
   cd day4/HealthClinicAppMySQLAndJDBC
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.healthclinicapp.App"
   ```