import java.util.List;


public interface ReplicationStrategy {

    void replicate(List<ReadReplica> replicas, MyRecord record);

}
