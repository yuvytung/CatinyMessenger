package org.regitiny.catiny.messenger.service.kafka.consumer;


import lombok.extern.log4j.Log4j2;
import org.regitiny.catiny.messenger.service.kafka.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MasterConsumer
{
//  đây là mẫu tùy biến
//  final Consumer<String, String> consumer;
//
//  public MasterConsumer(Consumer<String, String> consumer)
//  {
//    this.consumer = consumer;
//  }
//
//  @Bean(name = "requestKafkaListener_CatinyUAA_Master")
//  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> requestKafkaListenerContainerFactory()
//  {
//    return consumer.requestListenerContainerFactory();
//  }
//
//  @KafkaListener(topics = "topic", containerFactory = "requestKafkaListener_CatinyUAA_Master")
//  @SendTo
//  public String receive(String request)
//  {
//    log.debug(request);
//    return request;
//  }

  // reply mặc định gửi tin nhắn là: String

  @KafkaListener(topics = "topic", containerFactory = "requestListenerContainerFactory")
  @SendTo
  public String receive(String request)
  {
    log.debug(request);
    return request;
  }
}
