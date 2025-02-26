public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Prototyping Read Replicas!");

        // Choose replication strategy
        ReplicationStrategy strategy = new QuorumReplication(); // Change to AsyncReplication or SyncReplication as needed

        PrimaryDB primaryDB = new PrimaryDB(strategy);
        ReadReplica replica1 = new ReadReplica();
        ReadReplica replica2 = new ReadReplica();
        ReadReplica replica3 = new ReadReplica();
        ReadReplica replica4 = new ReadReplica();

        primaryDB.addReplica(replica1);
        primaryDB.addReplica(replica2);
        primaryDB.addReplica(replica3);
        primaryDB.addReplica(replica4);

        System.out.println("Initial Read from Replica 1: " + replica1.read(1).getData());

        primaryDB.write(1, "Updated Data 1");

        // Immediate read - should show stale data in async/quorum replication
        System.out.println("Immediate Read from Replica 1: " + replica1.read(1).getData());

        Thread.sleep(3000); // Wait for replication
        System.out.println("Post Replication Read from Replica 1: " + replica1.read(1).getData());

        // Simulating Primary DB failure
        primaryDB.failover();


    }
}
