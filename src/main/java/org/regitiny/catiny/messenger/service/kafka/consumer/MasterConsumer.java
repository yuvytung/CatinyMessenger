package org.regitiny.catiny.messenger.service.kafka.consumer;


import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.regitiny.catiny.messenger.domain.kafka.Master;
import org.regitiny.catiny.messenger.service.kafka.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MasterConsumer
{

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("groupId")
//  @Value("${kafka.master.groupId}")
  private String groupId;

  @Bean(name = "KafkaListenerContainerFactory_CatinyUAA_Master")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Master>> requestListenerContainerFactory()
  {
    return new Consumer<Master, Master>(bootstrapServers, groupId).requestListenerContainerFactory(Master.class);
  }

  @KafkaListener(topics = "topicx", containerFactory = "KafkaListenerContainerFactory_CatinyUAA_Master")
  @SendTo
  public Master receive(Master request)
  {
    log.info(request.toString());
    Master master = new Master();
    return master;
  }
}
