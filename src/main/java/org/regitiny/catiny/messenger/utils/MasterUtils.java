package org.regitiny.catiny.messenger.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.regitiny.catiny.messenger.security.SecurityUtils;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Log4j2
@Component
public class MasterUtils
{

  private static ReplyingKafkaTemplate<String, String, String> replyKafkaCatinyUAAMaster;

  @Autowired
  private void setReplyKafkaCatinyUAAMaster(ReplyingKafkaTemplate<String, String, String> replyKafkaCatinyUAAMaster)
  {
    MasterUtils.replyKafkaCatinyUAAMaster = replyKafkaCatinyUAAMaster;
  }

  public static MasterDTO getMaster()
  {
    String username = SecurityUtils.getCurrentUserLogin().orElse(null);
    log.debug("username = {}" , username);
    if (Objects.isNull(username))
      return null;

    MasterDTO masterDTO = new MasterDTO();
    masterDTO.setUserName(username);
    masterDTO.setGroupId(0L);
    masterDTO.setCompanyId(0L);

    log.info(replyKafkaCatinyUAAMaster);
    log.debug(masterDTO);
    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("GET_Request-Json_Entity-CatinyUAA.Master", masterDTO.toJsonString());
    RequestReplyFuture<String, String, String> replyFuture = replyKafkaCatinyUAAMaster.sendAndReceive(producerRecord);
    try
    {
      if (Objects.nonNull(replyFuture))
      {
        String result = replyFuture.get().value();
        return new MasterDTO().fromJson(result);
      }
    }
    catch (InterruptedException | ExecutionException e)
    {
      log.error("err detail: ", e);
    }

    return null;
  }

}
