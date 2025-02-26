import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ReadReplica {
    private final Map<Integer, MyRecord> records = new ConcurrentHashMap<>();

    public synchronized void update(MyRecord myRecord) {
        records.put(myRecord.getId(), myRecord);
    }

    public MyRecord read(int id) {
        return records.getOrDefault(id, new MyRecord(id, "Initial Data"));
    }

    public PrimaryDB promoteToPrimary(ReplicationStrategy strategy) {
        System.out.println("Replica promoted to Primary Database!");
        PrimaryDB newPrimary = new PrimaryDB(strategy);
        for (MyRecord myRecord : records.values()) {
            newPrimary.write(myRecord.getId(), myRecord.getData());
        }
        return newPrimary;
    }
}
