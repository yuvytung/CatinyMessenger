package org.regitiny.catiny.messenger.business;

import org.json.JSONObject;
import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;

import java.util.UUID;

public interface MessageBusiness
{
  /**
   *
   * @param topicId
   * @return
   */
  Boolean createMessageSimple(UUID topicId);


  JSONObject updateMessageSimple(String sender, String messageContent);
}
