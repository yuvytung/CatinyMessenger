package org.regitiny.catiny.messenger.service.kafka.producer;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.service.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MasterProducer
{

//  @Value("${kafka.producer.services.catiny-uaa.reply-topic}")
//  private String replyTopic;
//
//  private final Producer<String,String> producer;
//
//  public MasterProducer(Producer<String, String> producer)
//  {
//    this.producer = producer;
//  }
//
//  @SuppressWarnings("rawtypes")
//  @Bean(name = "ReplyingKafkaTemplate_CatinyUAA_Master")
//  public ReplyingKafkaTemplate replyKafkaTemplateImplementation()
//  {
//    return producer.replyKafkaTemplate(replyTopic);
//  }

  //  quy tắc đặt tên reply topic : <GET/SET>_<Reply/>-<Json/String>_<Entity/>-<Service>.<<Entity>/>
  //  quy tắc đặt tên method : <replyKafka/kafka>__<service reply>_<Entity>_<Other>
  @SuppressWarnings("rawtypes")
  @Bean
  public ReplyingKafkaTemplate replyKafkaCatinyUAAMaster(Producer<String,String> producer)
  {
    return producer.replyKafkaTemplate("GET_Reply-Json_Entity-CatinyUAA.Master");
  }

}
