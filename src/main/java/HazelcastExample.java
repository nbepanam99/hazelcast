import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import java.util.Map;

public class HazelcastExample {

    public static void main(String... args) {
        //initialize hazelcast server/instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        System.out.println("Hello world");
        Map<Long, String> map = hazelcastInstance.getMap("data");
        ;
        map.put(1L, "Tom");
        map.put(2L, "Bob");
        map.put(3L, "Anna");

        System.out.println("map size : " + map.size());

        // Using topics:
        ITopic<String> topic = hazelcastInstance.getTopic("users");

        topic.addMessageListener(message ->
                System.out.println(" -- User received" + message.toString()));


        System.out.println("-- Start publish user");
        map.forEach((aLong, s) -> {
            System.out.println("-- User :"+s);

            topic.publish(s);
        });

        // perform a graceful shutdown
        //hazelcastInstance.shutdown();
    }

}
