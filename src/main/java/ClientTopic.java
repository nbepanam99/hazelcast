import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

public class ClientTopic {

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance= HazelcastClient.newHazelcastClient();
        ITopic<String> topic = hazelcastInstance.getTopic("users");
        System.out.println("-- start listen Users");
        topic.addMessageListener(message ->
                System.out.println(" -- User received" + message.toString()));



    }

}

