# Database Read Replicas with Replication Strategies & Failover

You are designing a distributed database system where a Primary Database handles writes, and multiple Read Replicas serve read queries. The system should support various replication strategies and ensure high availability in case of primary failure.

## Requirements
- Replication Strategies:
  * Asynchronous Replication (Eventual Consistency): Write to the primary first and propagate changes to replicas asynchronously.
  * Synchronous Replication (Strong Consistency): Ensure all replicas are updated before committing the write.
  * Quorum-Based Replication: Wait for a quorum (majority) of replicas to acknowledge the update before committing.

- Read Handling:
  * Read queries should always be served from replicas to reduce load on the primary.

- Failover Mechanism:
  * If the Primary Database fails, one of the replicas should be promoted to Primary after a Leader Election process.
