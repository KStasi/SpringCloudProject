package ua.akondaur.client;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import ua.akondaur.db.Goal;

@Component
public class KafkaProducerDemo {

    private final static String TOPIC_CREATED = "create.entity";
    private final static String TOPIC_UPDATED = "update.entity";
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }

    private static void sendMessage(Goal entity, String topic) throws Exception {
        final Producer<Long, String> producer = createProducer();

        try {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(topic, entity.id, entity.toString());

            RecordMetadata metadata = producer.send(record).get();

            System.out.printf("sent record(key=%s value=%s) meta(partition=%d, offset=%d)", record.key(),
                    record.value(), metadata.partition(), metadata.offset());

        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void sendCreateTopic(Goal entity) throws Exception {
        sendMessage(entity, TOPIC_UPDATED);
    }

    public static void sendUpdateTopic(Goal entity) throws Exception {
        sendMessage(entity, TOPIC_CREATED);
    }

}