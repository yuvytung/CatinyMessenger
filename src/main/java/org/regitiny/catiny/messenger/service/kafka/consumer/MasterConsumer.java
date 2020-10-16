package org.regitiny.catiny.messenger.service.kafka.consumer;


import lombok.extern.log4j.Log4j2;
import org.regitiny.catiny.messenger.constants.CacheName;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.regitiny.catiny.messenger.service.kafka.Consumer;

import org.regitiny.catiny.messenger.utils.MasterUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

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


  //  quy tắc đặt tên reply topic : requestKafka_<service request>_<service reply>_<Entity>_<Other>
  //  quy tắc đặt tên method : requestKafka__<service reply>_<Entity>_<Other>



  @KafkaListener(topics = "updateCache_CatinyUAA_Master", containerFactory = "listenerContainerFactory")
  public void receive(String request)
  {
    log.debug(request);
    MasterDTO masterDTO = new MasterDTO().fromJson(request);
    log.debug(masterDTO);
    MasterUtils.updateMasterDTO(masterDTO);
  }



}
