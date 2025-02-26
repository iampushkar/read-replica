import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class QuorumReplication implements ReplicationStrategy {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void replicate(List<ReadReplica> replicas, MyRecord record) {
        int quorum = (replicas.size() / 2) + 1;
        CountDownLatch latch = new CountDownLatch(quorum);

        for (ReadReplica replica : replicas) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000); // Simulating network delay
                    replica.update(record);
                    latch.countDown();
                    System.out.println("Replica updated with: " + record.getData());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            latch.await(); // Wait for quorum
            System.out.println("Quorum reached. Update committed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
