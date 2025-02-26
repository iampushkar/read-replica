import java.util.List;


public class SyncReplication implements ReplicationStrategy {

    @Override
    public void replicate(List<ReadReplica> replicas, MyRecord record) {

        for (ReadReplica replica : replicas) {
            replica.update(record);
        }

        System.out.println("All replicas updated synchronously with: " + record.getData());
    }
}
