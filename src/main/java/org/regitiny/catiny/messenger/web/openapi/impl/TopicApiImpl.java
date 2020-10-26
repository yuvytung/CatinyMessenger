package org.regitiny.catiny.messenger.web.openapi.impl;

import lombok.extern.log4j.Log4j2;

import org.json.JSONObject;
import org.regitiny.catiny.messenger.business.MessageBusiness;
import org.regitiny.catiny.messenger.business.TopicBusiness;
import org.regitiny.catiny.messenger.exception.NotExistException;
import org.regitiny.catiny.messenger.web.openapi.TopicApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
public class TopicApiImpl implements TopicApi
{
  private final TopicBusiness topicBusiness;
  private final MessageBusiness messageBusiness;

  public TopicApiImpl(TopicBusiness topicBusiness, MessageBusiness messageBusiness)
  {
    this.topicBusiness = topicBusiness;
    this.messageBusiness = messageBusiness;
  }

  @Override
  public ResponseEntity<String> createTopic(TopicCreateModel topic)
  {
    JSONObject result;
    if (topic.getRecipientIds() != null)
      result = topicBusiness.createTopic(topic.getRecipientIds(), topic.getTopicName());
    else
      throw new NotExistException();
    log.debug(result.toString());
    UUID topicId = UUID.fromString(result.getString("topicId"));
    result.put("createMessageSimpleStatus", messageBusiness.createMessageSimple(topicId));
    log.debug(result.toString());
    return ResponseEntity.ok(result.toString());
  }

  @Override
  public ResponseEntity<String> addRecipientToTopic(TopicAddModel topicAddModel)
  {
    if (topicAddModel.getRecipientIds().size() > 0 && topicAddModel.getTopicId() != null)
      return ResponseEntity.ok(topicBusiness.addRecipientToTopic(topicAddModel.getRecipientIds(), topicAddModel.getTopicId()).toString());
    throw new NotExistException("recipientIds and topicId required");
  }
}
