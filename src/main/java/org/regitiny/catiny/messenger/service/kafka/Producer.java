package org.regitiny.catiny.messenger.service.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Producer<Request, Reply>
{
  private final String bootstrapServers;

  private final String replyTopic;

  private final String groupId;

  public ReplyingKafkaTemplate<String, Request, Reply> replyKafkaTemplate(Class<? super Reply> targetType)
  {
    return new ReplyingKafkaTemplate<>(requestProducerFactory(), replyListenerContainer(targetType));
  }

  private ProducerFactory<String, Request> requestProducerFactory()
  {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(props);
  }

  private ConsumerFactory<String, Reply> replyConsumerFactory(Class<? super Reply> targetType)
  {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(targetType));
  }

  private KafkaMessageListenerContainer<String, Reply> replyListenerContainer(Class<? super Reply> targetType)
  {
    ContainerProperties containerProperties = new ContainerProperties(replyTopic);
    return new KafkaMessageListenerContainer<>(replyConsumerFactory(targetType), containerProperties);
  }
}
