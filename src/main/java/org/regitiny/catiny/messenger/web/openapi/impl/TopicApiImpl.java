package org.regitiny.catiny.messenger.web.openapi.impl;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.business.TopicBusiness;
import org.regitiny.catiny.messenger.exception.NotExistException;
import org.regitiny.catiny.messenger.web.openapi.TopicApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class TopicApiImpl implements TopicApi
{
  private final TopicBusiness topicBusiness;


  public TopicApiImpl(TopicBusiness topicBusiness)
  {
    this.topicBusiness = topicBusiness;
  }

  @Override
  public ResponseEntity<String> createTopic(TopicCreateModel topic)
  {
    if (topic.getRecipientIds() != null)
      return ResponseEntity.ok(topicBusiness.createTopic(topic.getRecipientIds(), topic.getTopicName()).toString());
    throw new NotExistException();
  }

  @Override
  public ResponseEntity<String> addRecipientToTopic(TopicAddModel topicAddModel)
  {
    if (topicAddModel.getRecipientIds().size() > 0 && topicAddModel.getTopicId() != null)
      return ResponseEntity.ok(topicBusiness.addRecipientToTopic(topicAddModel.getRecipientIds(), topicAddModel.getTopicId()).toString());
    throw new NotExistException("recipientIds and topicId required");
  }
}
