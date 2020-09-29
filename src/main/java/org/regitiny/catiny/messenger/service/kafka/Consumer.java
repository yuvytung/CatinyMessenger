package org.regitiny.catiny.messenger.service.kafka;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Consumer<Request, Reply>
{
  private final String bootstrapServers;

  private final String groupId;

  private ProducerFactory<String, Reply> replyProducerFactory()
  {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(props);
  }

  private ConsumerFactory<String, Request> requestConsumerFactory(Class<? super Request> targetType)
  {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    props.put(JsonDeserializer.TRUSTED_PACKAGES,"org.regitiny.catiny.uaa.service.kafka.producer");

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(targetType));
  }

  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Request>> requestListenerContainerFactory(Class<? super Request> targetType)
  {
    ConcurrentKafkaListenerContainerFactory<String, Request> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(requestConsumerFactory(targetType));
    factory.setReplyTemplate(replyTemplate());
    return factory;
  }

  public KafkaTemplate<String, Reply> replyTemplate()
  {
    return new KafkaTemplate<>(replyProducerFactory());
  }
}

