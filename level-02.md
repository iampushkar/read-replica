🟡 Level 2: Adding Replication Strategies

1. Introduce ReplicationStrategy interface
2. Implement Asynchronous, Synchronous, and Quorum-Based Replication
3. Update PrimaryDatabase to use these strategies

```java

import java.util.concurrent.*;

interface ReplicationStrategy {
    void replicate(List<ReadReplica> replicas, Record record);
}

// Asynchronous Replication (Eventual Consistency)
class AsyncReplication implements ReplicationStrategy {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void replicate(List<ReadReplica> replicas, Record record) {
        for (ReadReplica replica : replicas) {
            executor.submit(() -> {
                try {
                    Thread.sleep(2000); // Simulating network delay
                    replica.update(record);
                    System.out.println("Async Replica updated: " + record.data);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}

// Synchronous Replication (Strong Consistency)
class SyncReplication implements ReplicationStrategy {
    public void replicate(List<ReadReplica> replicas, Record record) {
        for (ReadReplica replica : replicas) {
            replica.update(record);
        }
        System.out.println("All replicas updated synchronously: " + record.data);
    }
}

// Quorum-Based Replication (Majority Agreement)
class QuorumReplication implements ReplicationStrategy {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void replicate(List<ReadReplica> replicas, Record record) {
        int quorum = (replicas.size() / 2) + 1;
        CountDownLatch latch = new CountDownLatch(quorum);

        for (ReadReplica replica : replicas) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                    replica.update(record);
                    latch.countDown();
                    System.out.println("Quorum Replica updated: " + record.data);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            latch.await();
            System.out.println("Quorum reached. Update committed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```
