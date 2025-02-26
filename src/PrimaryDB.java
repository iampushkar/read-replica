import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class PrimaryDB {

    private final Map<Integer, MyRecord> records = new ConcurrentHashMap<>();
    private final List<ReadReplica> replicas = new ArrayList<>();
    private final ReplicationStrategy replicationStrategy;
    private boolean isActive = true;

    public PrimaryDB(final ReplicationStrategy replicationStrategy) {
        this.replicationStrategy = replicationStrategy;
    }

    public synchronized void write(int id, String newData) {
        if (!isActive) {
            throw new IllegalStateException("Primary DB is down!");
        }
        MyRecord myRecord = new MyRecord(id, newData);
        records.put(id, myRecord);
        System.out.println("Primary DB updated: " + myRecord.getData());
        replicationStrategy.replicate(replicas, myRecord);
    }

    public MyRecord read(int id) {
        if (!isActive) {
            throw new IllegalStateException("Primary DB is down!");
        }
        return records.getOrDefault(id, null);
    }

    public void addReplica(ReadReplica replica) {
        replicas.add(replica);
    }

    public void failover() {
        this.isActive = false;
        System.out.println("Primary DB has failed! Initiating leader election...");
        ReadReplica newPrimary = electNewLeader();
        if (newPrimary != null) {
            System.out.println("New leader elected!");
            PrimaryDB promotedPrimary = newPrimary.promoteToPrimary(replicationStrategy);
            replicas.remove(newPrimary);
        } else {
            System.out.println("No leader could be elected!");
        }
    }

    private ReadReplica electNewLeader() {
        if (replicas.isEmpty()) return null;
        return replicas.get(new Random().nextInt(replicas.size())); // Simple random leader election
    }
}
