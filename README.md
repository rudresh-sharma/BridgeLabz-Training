<div align="center">

# 🎨 Design Patterns & Multithreading

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Patterns-FF6B6B?style=for-the-badge&logo=java&logoColor=white)
![Multithreading](https://img.shields.io/badge/Multithreading-4CAF50?style=for-the-badge&logo=java&logoColor=white)
![SOLID](https://img.shields.io/badge/SOLID_Principles-2196F3?style=for-the-badge&logo=codacy&logoColor=white)

**Master software design principles, implement Gang of Four patterns, and develop concurrent programming skills**

[📂 View Repository](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-design-pattern-practice) • [🐛 Report Issue](https://github.com/rudresh-sharma/BridgeLabz-Training/issues) • [⭐ Star This Repo](https://github.com/rudresh-sharma/BridgeLabz-Training)

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

Welcome to **Week 7** of the BridgeLabz Java Training Program! This module focuses on **professional software design** through proven design patterns and **concurrent programming** with Java multithreading.

### 🌟 What Makes This Special?

<table>
<tr>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/module.png" width="64" alt="Design Patterns"/>
<h4>🏗️ Design Patterns</h4>
<p>Master Gang of Four patterns for elegant, maintainable solutions</p>
</td>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/rules.png" width="64" alt="SOLID"/>
<h4>📐 SOLID Principles</h4>
<p>Build robust systems with industry-standard design principles</p>
</td>
<td width="33%" align="center">
<img src="https://img.icons8.com/color/96/000000/parallel-tasks.png" width="64" alt="Multithreading"/>
<h4>⚡ Concurrency</h4>
<p>Harness the power of parallel processing and thread management</p>
</td>
</tr>
</table>

### 📅 Learning Timeline

> **📆 Learning Period:** January 31 - February 2, 2025  
> **🌿 Branch:** `java-design-pattern-practice`  
> **✅ Total Concepts Mastered:** 30+  
> **⏱️ Duration:** 3 Days Intensive Practice

---

## 📁 Project Structure

```plaintext
java-design-pattern-practice/
    |
    └───gcr-codebase
        └───JavaDesignPattternAndMultiThreadingPractice
            └───src
                └───com
                    ├───designpattern
                    │   ├───BookBuilderPattern
                    │   └───smartuniversitylibrarymanagementsystem
                    │       └───UMLDiagrams
                    └───multithreading
                        ├───banking
                        ├───downloadmanager
                        ├───printshopscheduler
                        ├───restaurantorders
                        └───threadstatemonitor
```

---

## 📚 Learning Journey

### 🗓️ Week 7: Solution Design & Concurrent Programming

> **🎯 Focus Areas:** SOLID Principles • Gang of Four Patterns • UML Design • Java Multithreading

---

<details open>
<summary><h3>📅 Day 1 - Design Principles (SOLID & More)</h3></summary>

**📆 Date:** January 31, 2025  
**🎯 Focus:** Software design foundations and industry-standard principles

#### 🎓 Topics Covered

**🔹 SOLID Principles**

```plaintext
S - Single Responsibility Principle (SRP)
    ✅ One class, one responsibility
    ✅ Separation of concerns
    ✅ Easier maintenance and testing

O - Open/Closed Principle (OCP)
    ✅ Open for extension, closed for modification
    ✅ Use abstraction and polymorphism
    ✅ Minimize code changes

L - Liskov Substitution Principle (LSP)
    ✅ Derived classes must be substitutable for base classes
    ✅ Behavioral subtyping
    ✅ Contract inheritance

I - Interface Segregation Principle (ISP)
    ✅ Client-specific interfaces
    ✅ No fat interfaces
    ✅ Role-based interface design

D - Dependency Inversion Principle (DIP)
    ✅ Depend on abstractions, not concretions
    ✅ High-level modules independent of low-level modules
    ✅ Inversion of control
```

**🔹 Additional Principles**

| Principle | Description | Implementation |
|-----------|-------------|----------------|
| **DRY** | Don't Repeat Yourself | Eliminate code duplication through abstraction |
| **KISS** | Keep It Simple, Stupid | Favor simplicity over complexity |
| **YAGNI** | You Aren't Gonna Need It | Don't build features you don't need yet |

#### 💡 Real-World Applications

<table>
<tr>
<td width="50%">

**✅ SRP Example: User Management**
```java
// Bad: Multiple responsibilities
class User {
    void saveToDatabase() { }
    void sendEmail() { }
    void generateReport() { }
}

// Good: Single responsibility
class UserRepository {
    void save(User user) { }
}
class EmailService {
    void send(User user) { }
}
class ReportGenerator {
    void generate(User user) { }
}
```

</td>
<td width="50%">

**✅ OCP Example: Payment Processing**
```java
interface PaymentMethod {
    void processPayment(double amount);
}

class CreditCard implements PaymentMethod {
    public void processPayment(double amount) {
        // Process credit card
    }
}

class PayPal implements PaymentMethod {
    public void processPayment(double amount) {
        // Process PayPal
    }
}
```

</td>
</tr>
</table>


</details>

---

<details>
<summary><h3>📅 Day 2 - Gang of Four Design Patterns</h3></summary>

**📆 Date:** January 31, 2025  
**🎯 Focus:** Creational, Structural, and Behavioral pattern implementation

#### 🎓 Topics Covered

### 🎨 Creational Patterns (Object Creation)

<table>
<tr>
<th width="25%">Pattern</th>
<th width="35%">Purpose</th>
<th width="40%">Use Case</th>
</tr>
<tr>
<td><b>Singleton</b></td>
<td>Ensure only one instance exists</td>
<td>Database connections, Configuration managers, Logger</td>
</tr>
<tr>
<td><b>Factory</b></td>
<td>Create objects without specifying exact class</td>
<td>Vehicle manufacturing, Document creation, UI components</td>
</tr>
<tr>
<td><b>Abstract Factory</b></td>
<td>Create families of related objects</td>
<td>Cross-platform UI kits, Database adapters</td>
</tr>
<tr>
<td><b>Builder</b></td>
<td>Construct complex objects step by step</td>
<td>User profiles, Query builders, Meal combos</td>
</tr>
<tr>
<td><b>Prototype</b></td>
<td>Clone existing objects</td>
<td>Game character templates, Document templates</td>
</tr>
</table>

#### 💻 Code Example: Singleton Pattern

```java
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection() {
        // Private constructor
        this.connection = createConnection();
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
}
```

### 🏗️ Structural Patterns (Object Composition)

<table>
<tr>
<th width="25%">Pattern</th>
<th width="35%">Purpose</th>
<th width="40%">Use Case</th>
</tr>
<tr>
<td><b>Adapter</b></td>
<td>Convert interface to another interface</td>
<td>Legacy system integration, Third-party libraries</td>
</tr>
<tr>
<td><b>Decorator</b></td>
<td>Add functionality dynamically</td>
<td>Coffee customization, Text formatting, Stream I/O</td>
</tr>
<tr>
<td><b>Facade</b></td>
<td>Simplify complex subsystems</td>
<td>Home theater system, Computer startup, Order processing</td>
</tr>
<tr>
<td><b>Proxy</b></td>
<td>Control access to objects</td>
<td>Virtual proxy (lazy loading), Protection proxy, Remote proxy</td>
</tr>
<tr>
<td><b>Composite</b></td>
<td>Tree structures of objects</td>
<td>File systems, Organization hierarchy, UI components</td>
</tr>
<tr>
<td><b>Bridge</b></td>
<td>Separate abstraction from implementation</td>
<td>Device drivers, Drawing shapes, Remote controls</td>
</tr>
</table>

#### 💻 Code Example: Decorator Pattern

```java
interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
    public double getCost() {
        return 2.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
    
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}
```

### 🎭 Behavioral Patterns (Object Interaction)

<table>
<tr>
<th width="25%">Pattern</th>
<th width="35%">Purpose</th>
<th width="40%">Use Case</th>
</tr>
<tr>
<td><b>Observer</b></td>
<td>Notify dependent objects of changes</td>
<td>Event handling, Newsletter subscriptions, Stock price monitoring</td>
</tr>
<tr>
<td><b>Strategy</b></td>
<td>Define family of algorithms</td>
<td>Payment methods, Sorting algorithms, Compression strategies</td>
</tr>
<tr>
<td><b>Command</b></td>
<td>Encapsulate requests as objects</td>
<td>Undo/Redo, Remote controls, Transaction processing</td>
</tr>
<tr>
<td><b>Template Method</b></td>
<td>Define algorithm skeleton</td>
<td>Data processing pipelines, Game AI, Report generation</td>
</tr>
<tr>
<td><b>State</b></td>
<td>Change behavior based on state</td>
<td>Vending machines, TCP connections, Order workflow</td>
</tr>
<tr>
<td><b>Iterator</b></td>
<td>Access elements sequentially</td>
<td>Collection traversal, Menu navigation, Playlist iteration</td>
</tr>
</table>

#### 💻 Code Example: Observer Pattern

```java
interface Observer {
    void update(String stockSymbol, double price);
}

class StockMarket {
    private List<Observer> observers = new ArrayList<>();
    private Map<String, Double> stockPrices = new HashMap<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    public void updateStockPrice(String symbol, double price) {
        stockPrices.put(symbol, price);
        notifyObservers(symbol, price);
    }
    
    private void notifyObservers(String symbol, double price) {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }
}

class Investor implements Observer {
    private String name;
    
    public Investor(String name) {
        this.name = name;
    }
    
    public void update(String stockSymbol, double price) {
        System.out.println(name + " notified: " + stockSymbol + 
                          " is now $" + price);
    }
}
```

**🔗 View Code:** [Design Patterns](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-design-pattern-practice/java-design-pattern-practice/gcr-codebase/JavaDesignPattternAndMultiThreadingPractice/src/com/designpattern)

</details>

---

<details>
<summary><h3>📅 Day 2 - UML Designing</h3></summary>

**📆 Date:** January 31, 2025  
**🎯 Focus:** Visual software architecture and design documentation

#### 🎓 Topics Covered

```plaintext
✅ Class Diagrams
   - Classes, Interfaces, Abstract Classes
   - Attributes and Methods
   - Visibility modifiers (+, -, #, ~)

✅ Relationships
   - Association (uses-a)
   - Aggregation (has-a, weak)
   - Composition (has-a, strong)
   - Inheritance (is-a)
   - Realization (implements)
   - Dependency (depends-on)

✅ Sequence Diagrams
   - Object interactions over time
   - Message passing
   - Lifelines and activation boxes
   - Return messages

✅ Pattern Visualization
   - Singleton pattern structure
   - Factory pattern hierarchy
   - Observer pattern interaction
   - Decorator pattern composition
```

#### 📊 UML Diagram Examples

**Class Diagram - Library Management System**

```
┌─────────────────┐         ┌─────────────────┐
│     Library     │◆────────│      Book       │
├─────────────────┤         ├─────────────────┤
│ - books: List   │         │ - ISBN: String  │
├─────────────────┤         │ - title: String │
│ + addBook()     │         │ - author: String│
│ + removeBook()  │         ├─────────────────┤
│ + findBook()    │         │ + getDetails()  │
└─────────────────┘         └─────────────────┘
        △                           △
        │                           │
        │                           │
┌───────┴─────────┐         ┌───────┴─────────┐
│  DigitalLibrary │         │   PhysicalBook  │
└─────────────────┘         └─────────────────┘
```


</details>

---

<details>
<summary><h3>📅 Day 3 - Java Multithreading</h3></summary>

**📆 Date:** February 2, 2025  
**🎯 Focus:** Concurrent programming and thread management

#### 🎓 Topics Covered

### 🧵 Thread Fundamentals

<table>
<tr>
<th width="30%">Concept</th>
<th width="70%">Description</th>
</tr>
<tr>
<td><b>Thread Creation</b></td>
<td>
• Extending Thread class<br>
• Implementing Runnable interface<br>
• Using Lambda expressions<br>
• Thread naming and priorities
</td>
</tr>
<tr>
<td><b>Thread Lifecycle</b></td>
<td>
• New → Runnable → Running → Blocked/Waiting → Terminated<br>
• start(), run(), sleep(), join(), interrupt()<br>
• Thread states and transitions
</td>
</tr>
<tr>
<td><b>Thread Priority</b></td>
<td>
• MIN_PRIORITY (1), NORM_PRIORITY (5), MAX_PRIORITY (10)<br>
• setPriority(), getPriority()<br>
• Platform-dependent scheduling
</td>
</tr>
</table>

#### 💻 Code Example: Thread Creation

```java
// Method 1: Extending Thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

// Method 2: Implementing Runnable
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}

// Usage
MyThread thread1 = new MyThread();
thread1.start();

Thread thread2 = new Thread(new MyRunnable());
thread2.start();

// Method 3: Lambda expression (Java 8+)
Thread thread3 = new Thread(() -> {
    System.out.println("Lambda thread: " + Thread.currentThread().getName());
});
thread3.start();
```

### 🔒 Synchronization & Thread Safety

<table>
<tr>
<th width="30%">Technique</th>
<th width="70%">Purpose & Usage</th>
</tr>
<tr>
<td><b>synchronized keyword</b></td>
<td>
• Method-level synchronization<br>
• Block-level synchronization<br>
• Prevents race conditions<br>
• Automatic lock acquisition/release
</td>
</tr>
<tr>
<td><b>wait() / notify()</b></td>
<td>
• Inter-thread communication<br>
• Producer-Consumer pattern<br>
• Thread coordination<br>
• Must be called within synchronized context
</td>
</tr>
<tr>
<td><b>Volatile keyword</b></td>
<td>
• Visibility guarantee across threads<br>
• No caching of variable<br>
• Lightweight synchronization<br>
• Atomic reads and writes
</td>
</tr>
<tr>
<td><b>Deadlock Prevention</b></td>
<td>
• Resource ordering<br>
• Lock timeout<br>
• Deadlock detection<br>
• Avoid nested locks
</td>
</tr>
</table>

#### 💻 Code Example: Synchronization

```java
class BankAccount {
    private double balance = 1000;
    
    // Method synchronization
    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + 
                              " withdrawing " + amount);
            balance -= amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    // Block synchronization
    public void deposit(double amount) {
        synchronized(this) {
            balance += amount;
            System.out.println("Deposited: " + amount + 
                              ", Balance: " + balance);
        }
    }
}
```

### ⚙️ Advanced Concurrency (java.util.concurrent)

<table>
<tr>
<th width="30%">Utility</th>
<th width="70%">Description & Use Cases</th>
</tr>
<tr>
<td><b>Executor Framework</b></td>
<td>
• ThreadPoolExecutor - Manage thread pools<br>
• FixedThreadPool - Fixed number of threads<br>
• CachedThreadPool - Creates threads as needed<br>
• ScheduledExecutorService - Scheduled tasks
</td>
</tr>
<tr>
<td><b>Callable & Future</b></td>
<td>
• Return values from threads<br>
• Exception handling in threads<br>
• Async computation results<br>
• get(), isDone(), cancel()
</td>
</tr>
<tr>
<td><b>Locks</b></td>
<td>
• ReentrantLock - Explicit lock/unlock<br>
• ReadWriteLock - Multiple readers, single writer<br>
• tryLock() - Non-blocking lock attempt<br>
• Condition variables
</td>
</tr>
<tr>
<td><b>Concurrent Collections</b></td>
<td>
• ConcurrentHashMap - Thread-safe map<br>
• CopyOnWriteArrayList - Thread-safe list<br>
• BlockingQueue - Producer-consumer<br>
• ConcurrentLinkedQueue - Non-blocking queue
</td>
</tr>
<tr>
<td><b>Synchronizers</b></td>
<td>
• CountDownLatch - Wait for multiple threads<br>
• CyclicBarrier - Synchronization point<br>
• Semaphore - Control access to resources<br>
• Phaser - Advanced synchronization
</td>
</tr>
<tr>
<td><b>Atomic Variables</b></td>
<td>
• AtomicInteger, AtomicLong, AtomicBoolean<br>
• Lock-free thread-safe operations<br>
• compareAndSet(), incrementAndGet()<br>
• Better performance than synchronized
</td>
</tr>
</table>

#### 💻 Code Example: Executor Framework

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

// Submit tasks
for (int i = 0; i < 5; i++) {
    int taskId = i;
    executor.submit(() -> {
        System.out.println("Task " + taskId + " executed by " + 
                          Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
}

// Shutdown executor
executor.shutdown();
try {
    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
        executor.shutdownNow();
    }
} catch (InterruptedException e) {
    executor.shutdownNow();
}
```

#### 💻 Code Example: Callable & Future

```java
ExecutorService executor = Executors.newSingleThreadExecutor();

Callable<Integer> task = () -> {
    Thread.sleep(2000);
    return 42;
};

Future<Integer> future = executor.submit(task);

System.out.println("Task submitted");

try {
    Integer result = future.get(); // Blocking call
    System.out.println("Result: " + result);
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}

executor.shutdown();
```

**🔗 View Code:** [Multithreading](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-design-pattern-practice/java-design-pattern-practice/gcr-codebase/JavaDesignPattternAndMultiThreadingPractice/src/com/multithreading)

</details>

---

<details>
<summary><h3>📚 Case Study: Smart University Library Management System</h3></summary>

**📆 Completion Date:** January 31, 2025  
**🎯 Objective:** Implement comprehensive LMS using multiple design patterns

#### 🏗️ System Architecture

**Design Patterns Used:**

| Pattern | Application in LMS |
|---------|-------------------|
| **Singleton** | Database connection, Configuration manager |
| **Factory** | Create different types of books (Physical, Digital, Audio) |
| **Observer** | Notify users about book availability |
| **Decorator** | Add features to books (Premium, Reserved) |
| **Strategy** | Different search strategies (by title, author, ISBN) |
| **Command** | Book checkout/return operations with undo |
| **Facade** | Simplified library operations interface |

#### 📋 Features Implemented

```plaintext
✅ User Management (Student, Faculty, Librarian)
✅ Book Catalog Management
✅ Check-out / Check-in System
✅ Search & Filter Functionality
✅ Fine Calculation System
✅ Reservation System
✅ Notification System
✅ Report Generation
✅ Multi-threaded book search
✅ Concurrent user operations
```

#### 💻 Key Components

```java
// Singleton: Library Manager
public class LibraryManager {
    private static volatile LibraryManager instance;
    private List<Book> books;
    private List<User> users;
    
    private LibraryManager() { }
    
    public static LibraryManager getInstance() {
        if (instance == null) {
            synchronized(LibraryManager.class) {
                if (instance == null) {
                    instance = new LibraryManager();
                }
            }
        }
        return instance;
    }
}

// Observer: Notification System
interface BookObserver {
    void update(Book book);
}

class Student implements BookObserver {
    public void update(Book book) {
        System.out.println("Notification: " + book.getTitle() + 
                          " is now available!");
    }
}

// Strategy: Search Strategy
interface SearchStrategy {
    List<Book> search(String query, List<Book> books);
}

class TitleSearchStrategy implements SearchStrategy {
    public List<Book> search(String query, List<Book> books) {
        return books.stream()
                   .filter(b -> b.getTitle().contains(query))
                   .collect(Collectors.toList());
    }
}
```

**🔗 View Case Study:** [Library Management System](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-design-pattern-practice/java-design-pattern-practice/case-studies/LibraryManagementSystem)

</details>

---

## 🎓 Key Concepts Covered

<div align="center">

| 🎯 Category | 📚 Topics | ✅ Status |
|------------|-----------|-----------|
| **SOLID Principles** | SRP, OCP, LSP, ISP, DIP | Completed |
| **Other Principles** | DRY, KISS, YAGNI | Completed |
| **Creational Patterns** | Singleton, Factory, Abstract Factory, Builder, Prototype | Completed |
| **Structural Patterns** | Adapter, Decorator, Facade, Proxy, Composite, Bridge | Completed |
| **Behavioral Patterns** | Observer, Strategy, Command, Template, State, Iterator | Completed |
| **UML Diagrams** | Class Diagrams, Sequence Diagrams, Relationships | Completed |
| **Thread Basics** | Thread Creation, Lifecycle, Priorities, Scheduling | Completed |
| **Synchronization** | synchronized, wait/notify, Deadlock Prevention | Completed |
| **Concurrent Utils** | Executor, Locks, Atomic Variables, Concurrent Collections | Completed |
| **Advanced Threading** | Thread Pools, Callable/Future, Synchronizers | Completed |

</div>

---

## 🚀 How to Use This Repository

### 📋 Prerequisites

```plaintext
✅ Java Development Kit (JDK) 8 or higher
✅ IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
✅ Understanding of OOP concepts
✅ Basic knowledge of UML diagrams
✅ Git installed on your system
```

### 🔧 Setup Instructions

**1️⃣ Clone the Repository**
```bash
git clone https://github.com/rudresh-sharma/BridgeLabz-Training.git
cd BridgeLabz-Training
```

**2️⃣ Switch to Design Patterns Branch**
```bash
git checkout java-design-pattern-practice
```

**3️⃣ Navigate to Project Directory**
```bash
cd java-design-pattern-practice
```

**4️⃣ Open in Your IDE**
```bash
# For IntelliJ IDEA
idea .

# For VS Code
code .

# For Eclipse
eclipse .
```

### 📂 Exploring the Code

**🔹 Design Principles**
```bash
cd design-principles/

# Explore SOLID principles
cd solid/SingleResponsibility
cd solid/OpenClosed
cd solid/LiskovSubstitution
cd solid/InterfaceSegregation
cd solid/DependencyInversion

# Other principles
cd other-principles/
```

**🔹 Design Patterns**
```bash
cd design-patterns/

# Creational patterns
cd creational/Singleton
cd creational/Factory
cd creational/Builder

# Structural patterns
cd structural/Adapter
cd structural/Decorator
cd structural/Facade

# Behavioral patterns
cd behavioral/Observer
cd behavioral/Strategy
cd behavioral/Command
```

**🔹 Multithreading**
```bash
cd multithreading/

# Thread basics
cd basics/

# Synchronization
cd synchronization/

# Concurrent utilities
cd concurrent-utils/

# Executor framework
cd executor-framework/
```

### ▶️ Running Examples

**Option 1: Run Individual Pattern**
```bash
# Compile
javac -d bin src/patterns/creational/Singleton/SingletonDemo.java

# Run
java -cp bin patterns.creational.Singleton.SingletonDemo
```

**Option 2: Run Multithreading Example**
```bash
# Compile
javac -d bin src/multithreading/basics/ThreadCreationDemo.java

# Run
java -cp bin multithreading.basics.ThreadCreationDemo
```

**Option 3: Use IDE**
- Navigate to the desired Java file
- Right-click → Run 'ClassName.main()'

---

## 📊 Progress Tracker

### 📈 Weekly Progress

| Day | Topic | Concepts Covered | Status |
|:---:|-------|:----------------:|:------:|
| **Day 1** | SOLID & Design Principles | 8 | ✅ Completed |
| **Day 2** | Creational Patterns | 5 | ✅ Completed |
| **Day 2** | Structural Patterns | 6 | ✅ Completed |
| **Day 2** | Behavioral Patterns | 6 | ✅ Completed |
| **Day 2** | UML Design | 4 | ✅ Completed |
| **Day 3** | Multithreading Fundamentals | 10+ | ✅ Completed |
| **Day 3** | Case Study (LMS) | 1 | ✅ Completed |


---

## 🌟 Key Takeaways

<table>
<tr>
<td width="50%">

### 💡 What I Learned

- ✅ Professional software design principles
- ✅ When and how to apply design patterns
- ✅ Creating maintainable, scalable code
- ✅ Visual modeling with UML diagrams
- ✅ Thread-safe concurrent programming
- ✅ Best practices for multi-threaded apps

</td>
<td width="50%">

### 🚀 Skills Gained

- ✅ Design pattern implementation
- ✅ SOLID principle application
- ✅ UML diagram creation
- ✅ Thread synchronization
- ✅ Concurrent programming
- ✅ Enterprise-level system design

</td>
</tr>
</table>

---

## 📖 Learning Resources

### 📚 Recommended Books

- **Design Patterns: Elements of Reusable Object-Oriented Software** - Gang of Four
- **Head First Design Patterns** - Eric Freeman & Elisabeth Robson
- **Clean Code** - Robert C. Martin
- **Java Concurrency in Practice** - Brian Goetz

### 🔗 Useful Links

- [Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [SourceMaking - Design Patterns](https://sourcemaking.com/design_patterns)
- [Oracle Java Tutorials - Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Baeldung - Java Multithreading](https://www.baeldung.com/java-concurrency)

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

*"Design patterns are solutions to recurring problems; guidelines on how to tackle certain problems."*

---

**© 2025 Rudresh Sharma | BridgeLabz Fellowship Program**

</div>
