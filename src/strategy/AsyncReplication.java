import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncReplication implements ReplicationStrategy {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    @Override
    public void replicate(List<ReadReplica> replicas, MyRecord record) {
        for (ReadReplica replica : replicas) {
            executor.submit(() -> {
                try {
                    Thread.sleep(2000); // Simulating delay
                    replica.update(record);
                    System.out.println("Replica updated with: " + record.getData());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
