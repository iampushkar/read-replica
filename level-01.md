🟢 Level 1: Basic Read-Replica Setup

1. Create a Record class to represent a database row
2. Implement PrimaryDatabase to handle writes
3. Implement ReadReplica to handle reads

```java

import java.util.*;

class Record {
    int id;
    String data;
    long timestamp;

    public Record(int id, String data) {
        this.id = id;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}

// Primary Database (Handles Writes)
class PrimaryDatabase {
    private final Map<Integer, Record> records = new HashMap<>();
    private final List<ReadReplica> replicas = new ArrayList<>();

    public void write(int id, String data) {
        Record record = new Record(id, data);
        records.put(id, record);
        System.out.println("Primary DB updated: " + record.data);
        
        // Replication (To be added in Level 2)
        for (ReadReplica replica : replicas) {
            replica.update(record);
        }
    }

    public void addReplica(ReadReplica replica) {
        replicas.add(replica);
    }
}

// Read Replica (Handles Reads)
class ReadReplica {
    private final Map<Integer, Record> records = new HashMap<>();

    public void update(Record record) {
        records.put(record.id, record);
    }

    public Record read(int id) {
        return records.getOrDefault(id, new Record(id, "No Data"));
    }
}

// Testing Level 1
public class ReadReplicaDemo {
    public static void main(String[] args) {
        PrimaryDatabase primaryDB = new PrimaryDatabase();
        ReadReplica replica1 = new ReadReplica();
        primaryDB.addReplica(replica1);

        primaryDB.write(1, "Hello, World!");
        System.out.println("Replica Read: " + replica1.read(1).data);
    }
}

```

