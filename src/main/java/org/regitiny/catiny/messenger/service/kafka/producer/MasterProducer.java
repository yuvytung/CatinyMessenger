package org.regitiny.catiny.messenger.service.kafka.producer;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.service.kafka.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

import org.regitiny.catiny.messenger.domain.kafka.Master;

@Log4j2
@Service
public class MasterProducer
{
  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("topic")
//  @Value("${kafka.topic.car.reply}")
  private String replyTopic;

  @Value("groupId")
//  @Value("${kafka.master.groupId}")
  private String groupId;

  @Bean(name = "ReplyingKafkaTemplate_CatinyUAA_Master")
  public ReplyingKafkaTemplate<String, Master, Master> replyKafkaTemplateImplementation()
  {
    return new Producer<Master, Master>(bootstrapServers, replyTopic, groupId).replyKafkaTemplate(Master.class);
  }

}
