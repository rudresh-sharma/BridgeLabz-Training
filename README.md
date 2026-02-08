<div align="center">

# 🚀 Java 8 & Modern Features

![Java 8](https://img.shields.io/badge/Java_8-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Lambda](https://img.shields.io/badge/Lambda_Expressions-4CAF50?style=for-the-badge&logo=java&logoColor=white)
![Streams](https://img.shields.io/badge/Stream_API-2196F3?style=for-the-badge&logo=java&logoColor=white)
![Functional](https://img.shields.io/badge/Functional_Programming-FF6B6B?style=for-the-badge&logo=java&logoColor=white)

**Master functional programming paradigms and unlock the power of modern Java development**

[📂 View Repository](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice) • [🐛 Report Issue](https://github.com/rudresh-sharma/BridgeLabz-Training/issues) • [⭐ Star This Repo](https://github.com/rudresh-sharma/BridgeLabz-Training)

</div>

---

## 📑 Table of Contents

- [🎯 Overview](#-overview)
- [📁 Project Structure](#-project-structure)
- [📚 Learning Journey](#-learning-journey)
- [🎓 Key Concepts Covered](#-key-concepts-covered)
- [🚀 How to Use This Repository](#-how-to-use-this-repository)
- [📊 Progress Tracker](#-progress-tracker)
- [📞 Connect & Collaborate](#-connect--collaborate)

---

## 🎯 Overview

Welcome to **Week 6** of the BridgeLabz Java Training Program! This module explores **Java 8's revolutionary features** that transformed Java into a modern, expressive, and functional programming language.

### 🌟 What Makes This Special?

<table>
<tr>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/lambda.png" width="64" alt="Lambda"/>
<h4>🔥 Functional Programming</h4>
<p>Master lambda expressions and method references for cleaner, more expressive code</p>
</td>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/data-stream.png" width="64" alt="Streams"/>
<h4>💧 Stream API</h4>
<p>Transform data processing with powerful declarative pipeline operations</p>
</td>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/code.png" width="64" alt="Real World"/>
<h4>🌍 Real-World Scenarios</h4>
<p>50+ practical problems from healthcare, e-commerce, IoT, and finance domains</p>
</td>
</tr>
</table>

### 📅 Learning Timeline

> **📆 Learning Period:** January 27-28, 2025  
> **🌿 Branch:** `java8-programming-practice`  
> **✅ Total Problems Solved:** 50+  
> **⏱️ Duration:** 2 Days Intensive Practice

---

## 📁 Project Structure

```plaintext
java8-programming-practice/
│
├── 📂 gcr-codebase/
│   └── Java8Concepts/
│       └── src/com/
│           ├── 🔹 collectors/              # Grouping, partitioning & aggregations
│           ├── 🔹 functionalinterfaces/    # Predicate, Function, Consumer, Supplier
│           ├── 🔹 LambdaExpressions/       # Lambda syntax & implementations
│           ├── 🔹 methodreferences/        # Static, instance & constructor references
│           └── 🔹 streamapi/               # Filter, map, reduce operations
│               └── foreach/                # Iteration patterns
│
└── 📂 scenario-base/
    └── java8scenario/
        └── src/com/streamapi/
            ├── 📊 employeestreamscenario/     # Employee data analysis
            ├── 📊 logicalquestions1/           # Complex stream challenges
            └── 📊 studentstreamscenario/       # Student performance analytics
```

---

## 📚 Learning Journey

### 🗓️ Week 6: Java 8 & Functional Programming Mastery

> **🎯 Focus Areas:** Lambda Expressions • Functional Interfaces • Stream API • Collectors • Method References

---

<details open>
<summary><h3>📅 Day 1 - Lambda Expressions & Method References (Part 1)</h3></summary>

**📆 Date:** January 27, 2025  
**🎯 Focus:** Functional programming foundations with lambda syntax and method references

#### 🎓 Topics Covered
```
✅ Lambda expression syntax and structure
✅ Functional programming paradigms
✅ Lambda vs Anonymous classes comparison
✅ Method references (Static, Instance, Constructor)
✅ Type inference and variable capture
✅ Scope and closures in lambda expressions
```

#### 💡 Real-World Scenarios Solved

<table>
<tr>
<th width="50%">🔸 Lambda Expressions (3 Problems)</th>
<th width="50%">🔸 Method References (3 Problems)</th>
</tr>
<tr>
<td valign="top">

**1. 🏠 Smart Home Lighting Automation**  
Define light activation behaviors for motion, time, and voice triggers dynamically without creating multiple classes

**2. 🛒 Custom E-Commerce Sorting**  
Implement dynamic product sorting with lambda-based Comparator (by price, rating, discount)

**3. 🏥 Hospital Notification Filtering**  
Filter patient alerts using lambda expressions with Predicate based on user preferences

</td>
<td valign="top">

**1. 🏥 Patient ID Printing**  
Print all patient IDs from a list for admin verification using method references

**2. 📝 Name Uppercasing**  
Convert employee names to uppercase for HR letters using `String::toUpperCase`

**3. 🧾 Invoice Object Creation**  
Generate invoice objects from transaction IDs using constructor references

</td>
</tr>
</table>

**🔗 View Code:** [Lambda Expressions](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/LambdaExpressions) • [Method References](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/methodreferences)

</details>

---

<details>
<summary><h3>📅 Day 1 - Functional Interfaces (Part 2)</h3></summary>

**📆 Date:** January 27, 2025  
**🎯 Focus:** Interface design patterns, functional interfaces, and Java 8 interface enhancements

#### 🎓 Topics Covered
```
✅ Defining and implementing interfaces (best practices)
✅ @FunctionalInterface annotation
✅ Built-in functional interfaces (Predicate, Function, Consumer, Supplier)
✅ Default and static methods in interfaces
✅ Marker interfaces
✅ BiFunction, BiConsumer, BiPredicate
```

#### 💡 Real-World Scenarios Solved

**🔹 Defining and Implementing Interfaces (3 Problems)**

| # | Scenario | Implementation |
|---|----------|----------------|
| 1 | 🏠 **Smart Device Control Interface** | Create interface for `turnOn()` and `turnOff()` methods across lights, AC, and TV |
| 2 | 🚗 **Multi-Vehicle Rental System** | Interface-based design for cars, bikes, and buses with `rent()` and `returnVehicle()` |
| 3 | 💳 **Digital Payment Interface** | Define and implement `pay()` method for UPI, Credit Card, and Wallet |

**🔹 Functional Interfaces (3 Problems)**

| # | Scenario | Functional Interface Used |
|---|----------|---------------------------|
| 1 | 🌡️ **Temperature Alert System** | `Predicate<Double>` - Alert if temperature crosses threshold |
| 2 | 📏 **String Length Checker** | `Function<String, Integer>` - Check if message exceeds character limit |
| 3 | ⚙️ **Background Job Execution** | `Runnable` - Execute tasks asynchronously |

**🔹 Static Methods in Interfaces (3 Problems)**

| # | Scenario | Use Case |
|---|----------|----------|
| 1 | 🔐 **Password Strength Validator** | Static method in `SecurityUtils` interface for password policy validation |
| 2 | 📦 **Unit Conversion Tool** | Static methods for standard unit conversions (km↔miles, kg↔lbs) |
| 3 | 📅 **Date Format Utility** | Static interface method to format dates in multiple formats |

**🔹 Default Methods in Interfaces (3 Problems)**

| # | Scenario | Solution |
|---|----------|----------|
| 1 | 💳 **Payment Gateway Integration** | Add default `refund()` method without breaking existing providers |
| 2 | 📊 **Data Export Feature** | Add default `exportToJSON()` method to existing CSV/PDF exporters |
| 3 | 🚗 **Smart Vehicle Dashboard** | Default method to display battery percentage for electric vehicles |

**🔹 Marker Interfaces (3 Problems)**

| # | Scenario | Marker Interface |
|---|----------|------------------|
| 1 | 💾 **Data Serialization for Backup** | `Serializable` for backup storage |
| 2 | 📋 **Cloning Prototype Objects** | `Cloneable` for object cloning |
| 3 | 🔒 **Sensitive Data Tagging** | Custom marker interface for encryption |

**🔗 View Code:** [Functional Interfaces](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/functionalinterfaces)

</details>

---

<details>
<summary><h3>📅 Day 2 - Stream API & forEach Method (Part 1)</h3></summary>

**📆 Date:** January 28, 2025  
**🎯 Focus:** Data transformation pipelines and declarative data processing

#### 🎓 Topics Covered
```
✅ Intermediate Operations: filter, map, flatMap, distinct, sorted, peek, limit, skip
✅ Terminal Operations: collect, forEach, reduce, count, anyMatch, allMatch, noneMatch
✅ Stateless vs Stateful operations
✅ Ordered vs Unordered streams
✅ Parallel streams & performance optimization
✅ forEach() — Iterable vs Stream comparison
```

#### 💡 Real-World Scenarios Solved

**🔹 Stream API Operations (5 Problems)**

<table>
<tr>
<td width="5%">1</td>
<td width="30%">🎬 <b>Top 5 Trending Movies</b></td>
<td>Find top 5 movies based on rating and release year using <code>filter()</code>, <code>sorted()</code>, <code>limit()</code></td>
</tr>
<tr>
<td>2</td>
<td>🏥 <b>Hospital Doctor Availability</b></td>
<td>Find weekend-available doctors and sort by specialty using streams with <code>filter()</code> and <code>sorted()</code></td>
</tr>
<tr>
<td>3</td>
<td>🏢 <b>Insurance Claim Analysis</b></td>
<td>Calculate average claim amount per claim type using <code>groupingBy()</code> and <code>averagingDouble()</code></td>
</tr>
<tr>
<td>4</td>
<td>💪 <b>Filtering Expiring Memberships</b></td>
<td>Filter gym members whose membership expires within 30 days using Stream API</td>
</tr>
<tr>
<td>5</td>
<td>👥 <b>Transforming Names for Display</b></td>
<td>Convert customer names to uppercase and sort alphabetically using <code>map()</code> and <code>sorted()</code></td>
</tr>
</table>

**🔹 forEach() Method (5 Problems)**

<table>
<tr>
<td width="5%">1</td>
<td width="30%">📈 <b>Stock Price Logger</b></td>
<td>Display all stock prices from live feed using <code>forEach()</code></td>
</tr>
<tr>
<td>2</td>
<td>🎉 <b>Event Attendee Welcome</b></td>
<td>Print welcome message for all attendees using <code>forEach()</code> with lambda</td>
</tr>
<tr>
<td>3</td>
<td>🌡️ <b>IoT Sensor Readings</b></td>
<td>Print readings above threshold using streams with <code>forEach()</code></td>
</tr>
<tr>
<td>4</td>
<td>📧 <b>Email Notifications</b></td>
<td>Send notification email to each user using <code>forEach()</code></td>
</tr>
<tr>
<td>5</td>
<td>📝 <b>Logging Transactions</b></td>
<td>Log each transaction with timestamp to console using <code>forEach()</code></td>
</tr>
</table>

**🔗 View Code:** [Stream API](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/streamapi) • [forEach](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/streamapi/foreach)

</details>

---

<details>
<summary><h3>📅 Day 2 - Collectors (Part 2)</h3></summary>

**📆 Date:** January 28, 2025  
**🎯 Focus:** Advanced data aggregation and grouping operations

#### 🎓 Topics Covered
```
✅ Collectors - Roles and capabilities
✅ toList(), toSet(), toMap()
✅ groupingBy(), partitioningBy()
✅ joining(), counting(), summarizing
✅ Custom collectors
✅ Downstream collectors
```

#### 💡 Real-World Scenarios Solved

**🔹 Collectors Operations (5 Problems)**

| # | Scenario | Collector Used | Description |
|---|----------|----------------|-------------|
| 1 | 🎓 **Student Result Grouping** | `groupingBy()` | Group students by grade level and collect names |
| 2 | 📝 **Word Frequency Counter** | `toMap()` | Analyze paragraph for word occurrence frequency |
| 3 | 💰 **Order Revenue Summary** | `summingDouble()` | Sum order totals per customer for revenue analysis |
| 4 | 👔 **Employee Salary Categorization** | `groupingBy()` + `averagingDouble()` | Calculate average salary per department |
| 5 | 📚 **Library Book Statistics** | `summarizingInt()` | Find total pages, average pages, and max pages per genre |

**💻 Code Example:**
```java
// Employee Salary Categorization
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));
```

**🔗 View Code:** [Collectors](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java8-programming-practice/java8-programming-practice/gcr-codebase/Java8Concepts/src/com/collectors)

</details>

---

## 🎓 Key Concepts Covered

<div align="center">

| 🎯 Category | 📚 Concepts | ✅ Status |
|------------|------------|-----------|
| **Lambda Expressions** | Syntax, Functional Programming, Type Inference, Variable Capture | Completed |
| **Method References** | Static References, Instance References, Constructor References | Completed |
| **Functional Interfaces** | Predicate, Function, Consumer, Supplier, BiFunction, Custom Interfaces | Completed |
| **Interface Enhancements** | Default Methods, Static Methods, Marker Interfaces | Completed |
| **Stream API** | Intermediate Operations (filter, map, flatMap, sorted, distinct, limit, skip) | Completed |
| **Stream API** | Terminal Operations (collect, forEach, reduce, count, match operations) | Completed |
| **Collectors** | groupingBy, partitioningBy, toList, toSet, toMap, joining, summarizing | Completed |
| **Advanced Streams** | Parallel Streams, Performance Optimization, Stateless vs Stateful | Completed |

</div>

---

## 🚀 How to Use This Repository

### 📋 Prerequisites

```plaintext
✅ Java Development Kit (JDK) 8 or higher
✅ IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
✅ Basic understanding of Java programming
✅ Git installed on your system
```

### 🔧 Setup Instructions

**1️⃣ Clone the Repository**
```bash
git clone https://github.com/rudresh-sharma/BridgeLabz-Training.git
cd BridgeLabz-Training
```

**2️⃣ Switch to Java 8 Branch**
```bash
git checkout java8-programming-practice
```

**3️⃣ Navigate to Project Directory**
```bash
cd java8-programming-practice
```

**4️⃣ Open in Your Favorite IDE**
```bash
# For IntelliJ IDEA
idea .

# For VS Code
code .

# For Eclipse
eclipse .
```

### 📂 Exploring the Code

**🔹 GCR Codebase (Core Concepts)**
```bash
cd gcr-codebase/Java8Concepts/src/com/

# Explore different modules:
cd LambdaExpressions      # Lambda syntax and examples
cd functionalinterfaces   # Functional interface implementations
cd streamapi              # Stream API operations
cd collectors             # Collector examples
cd methodreferences       # Method reference patterns
```

**🔹 Scenario-Based Problems**
```bash
cd scenario-base/java8scenario/src/com/streamapi/

# Real-world problem solutions:
cd employeestreamscenario    # Employee data analysis
cd studentstreamscenario     # Student performance analytics
cd logicalquestions1         # Complex stream challenges
```

### ▶️ Running Examples

**Option 1: Run Individual Classes**
```bash
# Compile
javac -d bin src/com/LambdaExpressions/SmartHomeLighting.java

# Run
java -cp bin com.LambdaExpressions.SmartHomeLighting
```

**Option 2: Use IDE**
- Navigate to the desired Java file
- Right-click → Run 'ClassName.main()'

---

## 📊 Progress Tracker

### 📈 Weekly Progress

| Day | Topic | Problems Solved | Status |
|:---:|-------|:---------------:|:------:|
| **Day 1** | Lambda Expressions & Method References | 6 | ✅ Completed |
| **Day 1** | Functional Interfaces (All Types) | 15 | ✅ Completed |
| **Day 2** | Stream API & forEach | 10 | ✅ Completed |
| **Day 2** | Collectors | 5 | ✅ Completed |
| **Bonus** | Scenario-Based Challenges | 14+ | ✅ Completed |




---

## 🌟 Key Takeaways

<table>
<tr>
<td width="50%">

### 💡 What I Learned

- ✅ Functional programming paradigms in Java
- ✅ Writing cleaner, more expressive code with lambdas
- ✅ Declarative data processing with Stream API
- ✅ Advanced interface design patterns
- ✅ Performance optimization with parallel streams
- ✅ Real-world application of Java 8 features

</td>
<td width="50%">

### 🚀 Skills Gained

- ✅ Lambda expression mastery
- ✅ Stream API proficiency
- ✅ Functional interface implementation
- ✅ Data transformation techniques
- ✅ Complex data aggregation
- ✅ Modern Java best practices

</td>
</tr>
</table>

---

## 📞 Connect & Collaborate

<div align="center">

### 🌐 Let's Connect!

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rudresh-sharma)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/rudresh-sharma)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:your.email@example.com)

---

### 💬 Feedback & Support

**Found this helpful? ⭐ Star this repository!**

[![Report Bug](https://img.shields.io/badge/Report-Bug-red?style=for-the-badge&logo=github)](https://github.com/rudresh-sharma/BridgeLabz-Training/issues)
[![Request Feature](https://img.shields.io/badge/Request-Feature-blue?style=for-the-badge&logo=github)](https://github.com/rudresh-sharma/BridgeLabz-Training/issues)
[![View Main Branch](https://img.shields.io/badge/View-Main_Branch-green?style=for-the-badge&logo=github)](https://github.com/rudresh-sharma/BridgeLabz-Training)

---

### 📜 License

This project is part of the **BridgeLabz Training Program** and is intended for educational purposes.

---

**Made with ❤️ and ☕ by Rudresh Sharma**

*"The only way to learn a new programming language is by writing programs in it." - Dennis Ritchie*

---

**© 2025 Rudresh Sharma | BridgeLabz Fellowship Program**

</div>
