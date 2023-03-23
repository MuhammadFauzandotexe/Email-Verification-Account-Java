package Controller;

import io.quarkus.logging.Log;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Properties;
@Path("kafka")
public class RegistrationKafka {
    @GET
    public String register(JsonObject body){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String,String> producer = new KafkaProducer<String,String>(properties);
        ProducerRecord<String,String> record = new ProducerRecord<>("topic-java",body.toString());
        producer.send(record);
        producer.close();
        Log.info(body.toString());
        return body.toString();
    }
}
