## Implement Multi-Leader Replication
Problem Statement:
* Extend your Read Replica System so that multiple databases can act as leaders
* If a leader fails, any other leader continues writing
* Implement Conflict Resolution Strategy when two leaders write different values

Concepts to Apply: Eventual Consistency, Multi-Leader Replication, Conflict Resolution


## Implement a Distributed Lock for Leader Election
Problem Statement:
* Modify your leader election mechanism to use a distributed lock
* Ensure only one node gets elected as leader at a time

Concepts to Apply: ZooKeeper, Redis-Based Locking, Paxos/Raft Algorithm


## Implement Strongly Consistent Replication using 2-Phase Commit
Problem Statement:
* Modify your synchronous replication strategy
* Implement a 2-Phase Commit (2PC) Protocol
* Ensure transactions are committed in all replicas or none

Concepts to Apply: ACID Transactions, Distributed Consensus, Commit Protocols

## Simulate Network Partition in Read Replica System
Problem Statement:
* Modify your code to simulate network failures
* Some replicas fail to receive updates
* Implement a strategy to handle partitioned replicas

Concepts to Apply: CAP Theorem, Partition Tolerance, Consistency Handling

## Implement Sharded Read-Replica System
Problem Statement:
* Instead of one primary DB, create multiple primary databases with different data partitions
* Each replica should only sync data from its corresponding primary
* Implement a router to direct queries to the right replica

Concepts to Apply: Sharding, Load Balancing, Query Routing
