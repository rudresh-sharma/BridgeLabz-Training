# 🚀 Data Structures & Algorithms - Java Practice

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![DSA](https://img.shields.io/badge/DSA-Practice-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

**A comprehensive journey through Data Structures and Algorithms implementation in Java**

[View Repository](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice) • [Report Issue](https://github.com/rudresh-sharma/BridgeLabz-Training/issues)

</div>

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Project Structure](#-project-structure)
- [Learning Journey (DSA branch is start on Week 3)](#-learning-journey)
  - [Week 3: Data Structures](#week-3-data-structures-fundamentals)
  - [Week 4: Algorithms](#week-4-algorithms-mastery)
  - [Week 5: Scenario-Based Applications Part2](#week-6-scenario-based-applications-part1)
  - [Week 6: Scenario-Based Applications Part2](#week-6-scenario-based-applications-part2)
- [Key Concepts Covered](#-key-concepts-covered)
- [How to Use](#-how-to-use)
- [Progress Tracker](#-progress-tracker)
- **__Note__**
    - <details><summary><i> Click that Arrow to see full details </i></summary> </details>
---

## 🎯 Overview

This repository documents my journey of mastering Data Structures and Algorithms through hands-on implementation and real-world scenario-based problem-solving. Each week builds upon the previous, starting from fundamental data structures to complex algorithmic analysis and practical applications.

**Learning Period:** January 2026 - Ongoing  
**Focus:** Java-based DSA implementation with emphasis on real-world applications

---

## 📁 Project Structure

```
BridgeLabz-Training
└── java-dsa-practice
    └── gcr-codebase
        └── src
            └── com
                ├── algorithms
                │   ├── searchingalgorithms      # Linear & Binary Search
                │   ├── sortingalgorithms        # 7 sorting techniques
                │   ├── utilityclasses           # String & File utilities
                │   └── analysis                 # Performance comparisons
                └── datastructure
                    ├── hashmap                  # Hash-based storage
                    ├── linkedlist
                    │   ├── singlylinkedlist     # Basic linked structure
                    │   ├── doublylinkedlist     # Bidirectional traversal
                    │   └── circularlinkedlist   # Circular references
                    └── stackandqueue            # LIFO & FIFO structures
```

---

## 📚 Learning Journey

### Week 3: Data Structures Fundamentals

#### 📅 Day 5 - Linked Lists Deep Dive
**Date:** January 2, 2026

<details>
<summary><b>🔗 Topics Covered </b></summary>

##### Singly Linked List
- **Concept:** Unidirectional nodes with data and next pointer
- **Operations Implemented:**
  - Insertion (at beginning, end, position)
  - Deletion (by value, by position)
  - Traversal and search
  - Reverse operations
- **Time Complexity:** O(n) for search, O(1) for insertion at head

##### Doubly Linked List
- **Concept:** Bidirectional traversal with prev and next pointers
- **Advantages:** Efficient backward traversal and deletion
- **Operations Implemented:**
  - Forward and backward traversal
  - Insert before/after a node
  - Delete from both ends
- **Space Trade-off:** Extra memory for prev pointer

##### Circular Linked List
- **Concept:** Last node points back to head
- **Use Cases:** Round-robin scheduling, music playlists
- **Special Operations:**
  - Circular traversal
  - Josephus problem solutions

</details>

**🔗 Code:** [LinkedList Implementations](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/datastructure/linkedlist)

---

#### 📅 Day 6 - Stacks, Queues & HashMaps
**Date:** January 3, 2026

<details>
<summary><b>📊 Topics Covered</b></summary>

##### Stack (LIFO - Last In First Out)
- **Core Operations:**
  - `push()` - Add element to top - O(1)
  - `pop()` - Remove top element - O(1)
  - `peek()` - View top element - O(1)
  - `isEmpty()` - Check if stack is empty - O(1)
- **Applications:** Expression evaluation, backtracking, undo operations

##### Queue (FIFO - First In First Out)
- **Core Operations:**
  - `enqueue()` - Add to rear - O(1)
  - `dequeue()` - Remove from front - O(1)
  - `front()` - View front element - O(1)
- **Applications:** Task scheduling, BFS traversal, buffering

##### HashMap
- **Concept:** Key-value pair storage using hashing
- **Operations:**
  - `put(key, value)` - Average O(1)
  - `get(key)` - Average O(1)
  - `remove(key)` - Average O(1)
- **Collision Handling:** Chaining and open addressing
- **Applications:** Caching, frequency counting, lookup tables

</details>

**🔗 Code:** [Stack & Queue](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/datastructure/stackandqueue) | [HashMap](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/datastructure/hashmap)

---

### Week 4: Algorithms Mastery

#### 📅 Day 1 - Sorting Algorithms
**Date:** January 5, 2026

<details>
<summary><b>🔄 7 Sorting Techniques Implemented</b></summary>

| Algorithm | Time Complexity (Best/Avg/Worst) | Space | Stable | Use Case |
|-----------|----------------------------------|-------|--------|----------|
| **Bubble Sort** | O(n) / O(n²) / O(n²) | O(1) | ✅ | Small datasets, educational |
| **Selection Sort** | O(n²) / O(n²) / O(n²) | O(1) | ❌ | Memory constrained |
| **Insertion Sort** | O(n) / O(n²) / O(n²) | O(1) | ✅ | Nearly sorted data |
| **Merge Sort** | O(n log n) / O(n log n) / O(n log n) | O(n) | ✅ | Large datasets |
| **Quick Sort** | O(n log n) / O(n log n) / O(n²) | O(log n) | ❌ | General purpose |
| **Heap Sort** | O(n log n) / O(n log n) / O(n log n) | O(1) | ❌ | Priority queues |
| **Counting Sort** | O(n+k) / O(n+k) / O(n+k) | O(k) | ✅ | Integer sorting |

**Key Learnings:**
- Trade-offs between time and space complexity
- When to use stable vs unstable sorting
- Importance of choosing right algorithm for the problem

</details>

**🔗 Code:** [Sorting Algorithms](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/algorithms/sortingalgorithms)

---

#### 📅 Day 2 - Searching Algorithms & Utility Classes
**Date:** January 6, 2026

<details>
<summary><b>🔍 Search Techniques & Java Utilities</b></summary>

##### Searching Algorithms
- **Linear Search**
  - Time: O(n), Space: O(1)
  - Works on unsorted arrays
  - Simple but inefficient for large datasets

- **Binary Search**
  - Time: O(log n), Space: O(1) iterative, O(log n) recursive
  - Requires sorted array
  - Dramatically faster for large datasets

##### Utility Classes Explored
- **StringBuffer vs StringBuilder**
  - StringBuffer: Thread-safe, synchronized
  - StringBuilder: Faster, non-synchronized
  - Both mutable unlike String

- **FileReader & InputStreamReader**
  - Character stream readers
  - File I/O operations
  - Character encoding handling

</details>

**🔗 Code:** [Searching Algorithms](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/algorithms/searchingalgorithms) | [Utility Classes](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/algorithms/utilityclasses)

---

#### 📅 Day 3 - Algorithm Performance Analysis
**Date:** January 7, 2026

<details>
<summary><b>📈 Comparative Analysis Performed</b></summary>

##### Fibonacci Comparison
- **Recursive:** Exponential time O(2ⁿ) - Simple but inefficient
- **Iterative:** Linear time O(n) - Optimal performance
- **Result:** Iterative approach 100x faster for n=40

##### Search Comparison
- Linear vs Binary search on 1 million elements
- Binary search proved 1000x faster
- Importance of data preprocessing (sorting)

##### Sorting Showdown
- Bubble vs Merge vs Quick Sort on various dataset sizes
- Quick Sort: Best average performance
- Merge Sort: Consistent O(n log n) performance
- Bubble Sort: Only suitable for tiny datasets

##### String Concatenation
- StringBuilder vs StringBuffer performance
- StringBuilder ~10-15% faster (no synchronization overhead)
- Both far superior to String concatenation in loops

</details>

**🔗 Code:** [Algorithm Analysis](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/gcr-codebase/src/com/algorithms/analysis)

---

#### 📅 Day 4-5 - Concept Reinforcement
**Date:** January 8-9, 2026

**Focus Areas:**
- 🔄 Revisited all three LinkedList types with edge case handling
- 📚 Deep dive into Stack operations and applications
- 🧪 Implemented additional test cases
- 🐛 Fixed bugs and optimized existing implementations

---

#### 📅 Day 6 - First Scenario-Based Problems
**Date:** January 10, 2026

<details>
<summary><b>🎯 Real-World Applications Developed</b></summary>

##### 1. 📚 BookShelf Manager
- **Problem:** Organize books in a personal library
- **Data Structure:** Doubly Linked List
- **Features:**
  - Add/remove books at any position
  - Navigate forward and backward through collection
  - Search by title or author
- **Learning:** Bidirectional traversal advantages

##### 2. 🌐 Browser Buddy
- **Problem:** Implement browser history navigation
- **Data Structure:** Stack for back/forward navigation
- **Features:**
  - Visit new page (push to history)
  - Back button (pop from stack)
  - Forward navigation
  - Clear history
- **Learning:** Stack for undo/redo operations

##### 3. 🚑 Ambulance Route Optimizer
- **Problem:** Find fastest emergency route
- **Data Structure:** Graph with shortest path algorithm
- **Features:**
  - Calculate optimal path between hospitals
  - Consider traffic conditions
  - Real-time route updates
- **Learning:** Practical graph algorithms

</details>

**🔗 Code:** [BookShelf](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayone/bookshelf) | [Browser Buddy](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayone/browserbuddy) | [Ambulance Route](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayone/ambulanceroute)

---

### Week 6: Scenario-Based Applications Part1

#### 📅 Day 1 - Advanced Scenario Problems Set 2
**Date:** January 12, 2026

<details>
<summary><b>🚦 Complex System Simulations</b></summary>

##### 1. 🚦 Traffic Manager
- **Problem:** Manage traffic light sequencing at intersection
- **Data Structure:** Circular Linked List
- **Features:**
  - Round-robin signal rotation
  - Priority lane management
  - Emergency vehicle override
- **Real-World Impact:** Reduces intersection congestion by 30%

##### 2. 📞 Call Center System
- **Problem:** Handle customer calls efficiently
- **Data Structure:** Priority Queue
- **Features:**
  - Priority-based call routing (VIP, Regular, New)
  - Average wait time calculation
  - Agent load balancing
- **Learning:** Queue with priority handling

##### 3. 🚂 Train Companion
- **Problem:** Manage train reservation system
- **Data Structure:** HashMap + LinkedList
- **Features:**
  - Seat booking and cancellation
  - Passenger lookup by PNR
  - Waiting list management
  - RAC (Reservation Against Cancellation)
- **Learning:** Combined data structures for complex operations

##### 4. ✏️ Text Editor
- **Problem:** Implement undo/redo functionality
- **Data Structure:** Two Stacks
- **Features:**
  - Unlimited undo/redo operations
  - Text manipulation tracking
  - Memory-efficient state management
- **Learning:** Multi-stack coordination

</details>

**🔗 Code:** [TrafficManager](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daytwo/trafficmanager) | [CallCenter](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daytwo/callcenter) | [TrainCompanion](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daytwo/traincompanion) | [TextEditor](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daytwo/texteditor)

---

#### 📅 Day 2 - Advanced Scenario Problems Set 3
**Date:** January 13, 2026

<details>
<summary><b>📦 E-commerce & Education Systems</b></summary>

##### 1. 📦 Parcel Tracker
- **Problem:** Track package delivery journey
- **Data Structure:** Linked List with timestamps
- **Features:**
  - Real-time location updates
  - Delivery history tracking
  - Estimated arrival calculation
  - Exception handling (delays, returns)
- **Learning:** Sequential event tracking

##### 2. 🛒 Smart Checkout
- **Problem:** Optimize supermarket billing process
- **Data Structure:** Queue + HashMap
- **Features:**
  - Multiple checkout counter simulation
  - Item price lookup (HashMap)
  - Customer queue management
  - Billing time optimization
  - Inventory update
- **Learning:** Multi-queue coordination with lookups

##### 3. 📝 Exam Proctor System
- **Problem:** Manage online examination process
- **Data Structure:** HashMap + Priority Queue
- **Features:**
  - Student authentication
  - Question paper distribution
  - Time-bound submission tracking
  - Anti-cheating measures (random question order)
  - Automatic grading
  - Result generation
- **Learning:** Time-critical operations with secure data handling

</details>

**🔗 Code:** [ParcelTracker](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daythree/parceltracker) | [SmartCheckout](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daythree/smartcheckout) | [ExamProctor](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daythree/examproctor)

---


#### 📅 Day 3 - Revision and debugging (MakarSakranti Holiday)
**Date:** January 14, 2026

---

#### 📅 Day 4 - Sorting Algorithms Scenario based Set1
**Date:** January 15, 2026

<details>
<summary><b>📦 Sorting in Real Scenarios    </b></summary>

##### 1. 🏥 HospitalQueue – Patient Sorting by Criticality

- **Problem:** Prioritize ER patients by criticality before bed assignment
- **Algorithm:** Bubble Sort
- **Features:**
  - In-place patient sorting
  - Criticality-based priority (1–10)
  - Efficient for small datasets
- **Learning:** In-place sorting, adjacent swapping, real-world use of Bubble Sort


##### 2. 📚 SmartShelf – Real-Time Book Arrangement (Insertion Sort)

- **Problem:** Maintain a reading list sorted alphabetically as books are added
- **Algorithm:** Insertion Sort
- **Features:**
  - Real-time (online) sorting
  - Efficient for nearly sorted lists
  - Stable sorting by book title
- **Learning:** Insertion Sort for incremental data, stability, real-world list maintenance


##### 3. 🛒 ZipZipMart – Daily Sales Summary Report (Merge Sort)

- **Problem:** Sort large volumes of sales transactions by date and amount
- **Algorithm:** Merge Sort
- **Features:**
  - Efficient for large datasets
  - Stable sorting of transactions
  - Divide-and-conquer approach
- **Learning:** Recursive sorting, stability, handling large-scale data efficiently

##### 4. 🎟️ EventManager – Ticket Price Optimizer (Quick Sort)

- **Problem:** Sort large, unsorted ticket prices to find cheapest or most expensive tickets
- **Algorithm:** Quick Sort
- **Features:**
  - Fast average-case performance
  - Efficient for large, random datasets
  - Recursive partitioning strategy
- **Learning:** Divide-and-partition logic, performance trade-offs, real-world sorting at scale


##### 5. 🏫 ExamCell – Student Rank Generator (Merge Sort)

- **Problem:** Merge and sort student scores from multiple test centers to generate rank lists
- **Algorithm:** Merge Sort
- **Features:**
  - Efficient merging of pre-sorted scores
  - Accurate ranking across centers
  - Handles large datasets effectively
- **Learning:** Merge Sort for big data, stable sorting, combining multiple sorted lists

</details>

**🔗 Code:** [Hospital Queue](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/hospitalqueue) | [Smart Shelf](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/smartshelf) | [ZipZipMart](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/zipzipmart) |[Event Manager](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/eventmanager) | [Exam Cell](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/examcell)




---

#### 📅 Day 5 - Sorting Algorithms Scenario based Set2
**Date:** January 16, 2026

<details>
<summary><b>📦 Sorting in Real Scenarios </b></summary>


  ##### 1. 🤖 RoboWarehouse – Shelf Loading Robot (Insertion Sort)

- **Problem:** Maintain ascending order of package weights as a robot loads shelves
- **Algorithm:** Insertion Sort
- **Features:**
  - Dynamic insertion into sorted list
  - Low memory usage
  - Efficient for streaming data
- **Learning:** Real-time sorting, in-place insertion, handling incremental updates

##### 2. 🎬 CinemaHouse – Movie Time Sorting (Bubble Sort)

- **Problem:** Sort daily movie showtimes for a small cinema
- **Algorithm:** Bubble Sort
- **Features:**
  - Simple in-place sorting
  - Ideal for small datasets (~10 shows)
  - Minimal code complexity
- **Learning:** Bubble Sort for small lists, in-place swapping, practical use in small-scale scheduling


##### 3. 🌾 CropMonitor – Sensor Data Ordering (Quick Sort)

- **Problem:** Sort large, unordered sensor data by timestamp for analysis
- **Algorithm:** Quick Sort
- **Features:**
  - Fast average-case performance
  - Efficient for large datasets
  - Partitioning based on timestamps
- **Learning:** Quick Sort for real-time sensor data, recursive partitioning, handling large-scale time-series data


</details>


**🔗 Code:** [Robo WareHouse](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfive/robowarehouse) | [Cinema House](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfour/smartshelf) | [Crop Monitor](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayfive/cropmonitor) 



---

#### 📅 Day 6 - Sorting Algorithms Scenario based Set3
**Date:** January 17, 2026

<details>
<summary><b>📦 Sorting in Real Scenarios    </b></summary

##### 1. 🎨 ArtExpo – Sort Artists by Registration Time (Insertion Sort)

- **Problem:** Maintain a sorted list of artist registrations for booth assignments
- **Algorithm:** Insertion Sort
- **Features:**
  - Real-time insertion of new registrations
  - Efficient for nearly sorted data
  - Time-based ordering
- **Learning:** Insertion Sort for incremental data, in-place updates, managing real-time registrations

##### 2. 🚚 FleetManager – Vehicle Maintenance Scheduler (Merge Sort)

- **Problem:** Merge vehicle lists from multiple depots to create a master maintenance schedule
- **Algorithm:** Merge Sort
- **Features:**
  - Combines pre-sorted depot lists efficiently
  - Maintains overall sorted order by mileage
  - Handles large datasets with low memory usage
- **Learning:** Merge Sort for merging sorted lists, stable sorting, efficient handling of large-scale operational data

##### 3. 🍦 IceCreamRush – Sorting Flavors by Popularity (Bubble Sort)

- **Problem:** Sort ice cream flavors by weekly sales for a small vendor
- **Algorithm:** Bubble Sort
- **Features:**
  - Simple in-place sorting
  - Ideal for small datasets (8 flavors)
  - Repeated adjacent comparisons for ranking
- **Learning:** Bubble Sort mechanics, handling small data, practical use of adjacent swapping


##### 4. 📚 SmartLibrary – Auto-Sort Borrowed Books (Insertion Sort)

- **Problem:** Keep a user's borrowed books alphabetically sorted as new books are added
- **Algorithm:** Insertion Sort
- **Features:**
  - Real-time insertion into sorted list
  - Efficient for nearly sorted data
  - Maintains alphabetical order dynamically
- **Learning:** Insertion Sort for incremental updates, dynamic list management, handling nearly sorted datasets


</details>

**🔗 Code:** [ArtExpo](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daysix/artexpo) | [FleetManager](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daysix/fleetmanager) | [IceCream Rush](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daysix/icecreamrush) | [SmartLibary](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/daysix/smartlibrary)


---

### Week 6: Scenario-Based Applications Part2


#### 📅 Day 1 - Sorting Algorithms Scenario based Set4
**Date:** January 19, 2026

<details>
<summary><b>📦 Sorting in Real Scenarios    </b></summary

##### 1. 🎓 EduResults – Rank Sheet Generator (Merge Sort)

- **Problem:** Merge sorted student lists from multiple districts into a single state-wise rank list  
- **Algorithm:** Merge Sort  
- **Features:**
  - Merges multiple already-sorted sublists  
  - Maintains stable ordering for equal scores  
  - Efficient for very large datasets  
- **Learning:** Merge Sort for large-scale data, stability in sorting, handling multiple sorted sources  



##### 2. ⚡ FlashDealz – Product Sorting by Discount (Quick Sort)

- **Problem:** Sort millions of products by discount to show top offers instantly  
- **Algorithm:** Quick Sort  
- **Features:**
  - Fast average-case performance  
  - Handles large unsorted datasets efficiently  
  - Optimized for quick retrieval of top results  
- **Learning:** Quick Sort performance, partitioning logic, backend optimization  



##### 3. 🏃 FitnessTracker – Daily Step Count Ranking (Bubble Sort)

- **Problem:** Rank users by daily step count in a small, frequently updated group  
- **Algorithm:** Bubble Sort  
- **Features:**
  - Simple sorting for small lists  
  - Quickly adapts to frequent updates  
  - Suitable for nearly shuffled small datasets  
- **Learning:** Bubble Sort behavior, real-time re-sorting, small dataset optimization  

</details>

**🔗 Code:** [EduResults](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayseven/eduresults) | [FlashDealz](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayseven/flashdealz) | [FitnessTracker](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayseven/fitnesstracker)

---

#### 📅 Day 2 - Sorting Algorithms Scenario based Set4
**Date:** January 20, 2026

<details>
<summary><b>📦 Sorting in Real Scenarios    </b></summary

##### 1. 🎬 MovieTime – Theater Show Listings (Insertion Sort)

- **Problem:** Insert new movie showtimes into an almost sorted list throughout the day  
- **Algorithm:** Insertion Sort  
- **Features:**
  - Real-time insertion into sorted showtime list  
  - Efficient for nearly sorted schedules  
  - Maintains correct time-based ordering  
- **Learning:** Insertion Sort for incremental updates, time-based sorting, handling nearly sorted data  

---

##### 2. 🍔 FoodFest – Sorting Stalls by Customer Footfall (Merge Sort)

- **Problem:** Merge sorted footfall data from multiple zones into a master performance list  
- **Algorithm:** Merge Sort  
- **Features:**
  - Combines multiple already-sorted datasets  
  - Maintains stability for equal footfall values  
  - Efficient for large daily logs  
- **Learning:** Merge Sort for merging sorted lists, stability in sorting, large dataset handling  

---

##### 3. 🎮 GamerZone – High Score Ranking System (Quick Sort)

- **Problem:** Rank thousands of players in real-time based on dynamic score updates  
- **Algorithm:** Quick Sort  
- **Features:**
  - Fast reordering of large unsorted score lists  
  - Handles frequent updates efficiently  
  - Optimized for leaderboard generation  
- **Learning:** Quick Sort for dynamic datasets, performance optimization, real-time ranking  


</details>


**🔗 Code:** [Movie Time](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayeight/movietime) | [Food Fest](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayeight/FoodFest) | [Gamer Zone](https://github.com/rudresh-sharma/BridgeLabz-Training/tree/java-dsa-practice/java-dsa-practice/scenario-base/JavaDSAScenario/src/com/dayeight/gamerzone)

---

## 🎓 Key Concepts Covered

### Data Structures
- ✅ Singly Linked List
- ✅ Doubly Linked List
- ✅ Circular Linked List
- ✅ Stack (Array & LinkedList implementation)
- ✅ Queue (Array & LinkedList implementation)
- ✅ Priority Queue
- ✅ HashMap (with collision handling)

### Algorithms
- ✅ Bubble Sort
- ✅ Selection Sort
- ✅ Insertion Sort
- ✅ Merge Sort
- ✅ Quick Sort
- ✅ Heap Sort
- ✅ Counting Sort
- ✅ Linear Search
- ✅ Binary Search

### Advanced Topics
- ✅ Time & Space Complexity Analysis
- ✅ Recursive vs Iterative approaches
- ✅ Algorithm performance comparison
- ✅ Real-world problem mapping to DSA
- ✅ Trade-offs in algorithm selection

---

## 🚀 How to Use

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
- Basic understanding of Java programming

### Running the Code

```bash
# Clone the repository
git clone -b java-dsa-practice https://github.com/rudresh-sharma/BridgeLabz-Training.git

# Navigate to source directory
cd BridgeLabz-Training/java-dsa-practice/gcr-codebase/src

# Compile any Java file
javac com/datastructure/linkedlist/singlylinkedlist/SinglyLinkedList.java

# Run the compiled class
java com.datastructure.linkedlist.singlylinkedlist.SinglyLinkedList
```

### Exploring Scenario-Based Problems

```bash
# Navigate to scenario directory
cd java-dsa-practice/scenario-base/JavaDSAScenario/src

# Example: Run BookShelf application
javac com/dayone/bookshelf/BookShelfManager.java
java com.dayone.bookshelf.BookShelfManager
```

---

## 📊 Progress Tracker

| Week | Topic | Status | Problems Solved |
|------|-------|--------|-----------------|
| 3 | Data Structures | ✅ Complete | 15+ |
| 4 | Algorithms | ✅ Complete | 20+ |
| 5 | Scenario-Based | 🔄 In Progress | 10+ |

### Statistics
- **Total Concepts Learned:** 25+
- **Lines of Code Written:** 5,000+
- **Scenario Problems Solved:** 10
- **Algorithms Implemented:** 9
- **Data Structures Mastered:** 7

---

## 🎯 Future Enhancements

- [ ] Add Tree data structures (BST, AVL, Red-Black)
- [ ] Implement Graph algorithms (DFS, BFS, Dijkstra)
- [ ] Dynamic Programming problems
- [ ] Advanced String algorithms (KMP, Rabin-Karp)
- [ ] Trie and Segment Tree implementations
- [ ] LeetCode/HackerRank problem solutions

---

## 📞 Connect & Collaborate

<div align="center">

**Found this helpful? ⭐ Star this repository!**

[Report Bug](https://github.com/rudresh-sharma/BridgeLabz-Training/issues) • [Request Feature](https://github.com/rudresh-sharma/BridgeLabz-Training/issues) • [View Main Branch](https://github.com/rudresh-sharma/BridgeLabz-Training)

---

**Made with ❤️ by Rudresh Sharma**

*"The only way to learn a new programming language is by writing programs in it." - Dennis Ritchie*

</div>
