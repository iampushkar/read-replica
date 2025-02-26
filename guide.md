# Read Replica System - Guide

This guide contains **pre-reads** to help you prepare for implementing the **Read Replica System with Replication Strategy & Failover** and **post-reads** to deepen your understanding after implementation.

---

## 📌 Pre:

### 1️⃣ Java Fundamentals for Distributed Systems
🔹 **Concurrency & Multithreading**
- Java’s `ExecutorService` and `ThreadPoolExecutor`
- `synchronized`, `volatile`, and `CountDownLatch`

🔹 **Collections & Concurrent Data Structures**
- `ConcurrentHashMap` (used in replicas)
- `CopyOnWriteArrayList` for thread-safe operations

---

### 2️⃣ Database Replication Basics
🔹 **What is Replication?**
- Types: **Synchronous, Asynchronous, Quorum-Based**
- Trade-offs: **Eventual Consistency vs Strong Consistency**

---

### 3️⃣ Leader Election Algorithms
🔹 **Why do we need Leader Election?**
- Handling **Primary Database Failure**
- Ensuring **Only One Leader** is Active at a Time

🔹 **Algorithms for Leader Election**
- **Bully Algorithm** – Simple & effective in small networks
- **Raft Consensus Algorithm** – Used in modern databases
- **Paxos Algorithm** – Theoretical foundation for distributed consensus

---

### 4️⃣ CAP Theorem & Consistency Models
🔹 **CAP Theorem (Consistency, Availability, Partition Tolerance)**
- Trade-offs: Why **Strong Consistency** sacrifices **Availability**

🔹 **Consistency Models**
- **Strong Consistency:** Writes are **instantly visible** everywhere
- **Eventual Consistency:** Writes **propagate over time**

---

### 5️⃣ Quorum-Based Replication & 2PC (Two-Phase Commit)
🔹 **Why Quorum-Based Replication?**
- Balancing **consistency & availability**
- Ensuring updates are committed **only when a majority agrees**

🔹 **How 2-Phase Commit (2PC) Works**
- **Phase 1: Prepare** – Coordinator asks replicas if they are ready
- **Phase 2: Commit** – If all agree, transaction is committed

---

## 📌 Post

### 1️⃣ Eventual vs Strong Consistency
- Amazon DynamoDB Paper
- CAP Theorem Explained

---

### 2️⃣ Leader Election Algorithms
- [Raft Consensus Algorithm](https://raft.github.io/)
- [Paxos Made Simple](https://lamport.azurewebsites.net/pubs/paxos-simple.pdf)

---

### 3️⃣ Database Replication & Failover
- [Google Spanner Whitepaper](https://research.google/pubs/spanner-google-distributed-database/)
- [MySQL Replication Explained](https://dev.mysql.com/doc/refman/8.0/en/replication.html)

---
