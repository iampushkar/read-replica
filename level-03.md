🔴 Level 3: Failover & Leader Election

1. Simulate Primary DB Failure
2. Implement Leader Election for failover

```java

class PrimaryDatabase {
    private final Map<Integer, Record> records = new ConcurrentHashMap<>();
    private final List<ReadReplica> replicas = new ArrayList<>();
    private final ReplicationStrategy replicationStrategy;
    private boolean isActive = true;

    public PrimaryDatabase(ReplicationStrategy strategy) {
        this.replicationStrategy = strategy;
    }

    public synchronized void write(int id, String data) {
        if (!isActive) {
            throw new IllegalStateException("Primary DB is down!");
        }
        Record record = new Record(id, data);
        records.put(id, record);
        replicationStrategy.replicate(replicas, record);
    }

    public void failover() {
        this.isActive = false;
        System.out.println("Primary DB failed! Electing new leader...");
        ReadReplica newPrimary = electNewLeader();
        if (newPrimary != null) {
            System.out.println("New leader elected!");
            newPrimary.promoteToPrimary(replicationStrategy);
        }
    }

    private ReadReplica electNewLeader() {
        if (replicas.isEmpty()) return null;
        return replicas.get(new Random().nextInt(replicas.size())); // Random leader election
    }
}

```
