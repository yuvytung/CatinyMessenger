package org.regitiny.catiny.messenger.business;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TopicBusiness
{

  /**
   * this method will create one topic
   * @param masterIds is a list recipientId .
   * @param topicName is name of topic.
   * @return JSONObject
   * @throws Exception
   */
  JSONObject createTopic(List<UUID> masterIds, String topicName) ;


  /**
   * this method will add all recipient to the topic if exists
   * @param recipientIds
   * @param topicId
   * @return
   */
  JSONObject addRecipientToTopic(List<UUID> recipientIds , UUID topicId);
}
