package org.regitiny.catiny.messenger;

import org.json.JSONObject;
import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

@SpringBootTest
public class Test
{
  @Autowired
  private MessageIdByRecipientTopicMapper mapper;
  @Autowired
  private MessageIdByRecipientTopicService service;

  @org.junit.jupiter.api.Test
  void testService() throws Exception
  {
    JSONObject jsonObject = new JSONObject();


    MessageIdByRecipientTopic messageIdByRecipientTopic = new MessageIdByRecipientTopic();
    messageIdByRecipientTopic.recipientId(UUID.randomUUID()).topicId(UUID.randomUUID()).createDate(Instant.now()).messageId(UUID.randomUUID()).messageStatus("sdsad").deletedStatus(true);
    jsonObject.put("create object", messageIdByRecipientTopic );
    System.out.println(messageIdByRecipientTopic.toString());
    System.out.println(mapper);

    MessageIdByRecipientTopicDTO dto = service.save(mapper.toDto(messageIdByRecipientTopic));
    jsonObject.put("createResult", dto.toJsonObject());
    MessageIdByRecipientTopicDTO m2 = service.fetchOne(messageIdByRecipientTopic.getRecipientId(),messageIdByRecipientTopic.getTopicId(),messageIdByRecipientTopic.getCreateDate(),messageIdByRecipientTopic.getMessageId());
    jsonObject.put("find one", m2.toJsonObject());
    jsonObject.put("find all", service.findAll());

    jsonObject.put("deleteStatus", service.delete(messageIdByRecipientTopic.getRecipientId(),messageIdByRecipientTopic.getTopicId(),messageIdByRecipientTopic.getCreateDate(),messageIdByRecipientTopic.getMessageId()));

  }
}
