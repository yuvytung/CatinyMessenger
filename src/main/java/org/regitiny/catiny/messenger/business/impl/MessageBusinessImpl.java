package org.regitiny.catiny.messenger.business.impl;

import org.json.JSONObject;
import org.regitiny.catiny.messenger.business.MessageBusiness;
import org.regitiny.catiny.messenger.constants.MessageDefault;
import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;
import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.domain.RecipientByTopic;
import org.regitiny.catiny.messenger.exception.NhechException;
import org.regitiny.catiny.messenger.service.MessageByTopicMessageIdService;
import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.service.MessageSimpleByRecipientService;
import org.regitiny.catiny.messenger.service.RecipientByTopicService;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageByTopicMessageIdMapper;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;
import org.regitiny.catiny.messenger.service.mapper.MessageSimpleByRecipientMapper;
import org.regitiny.catiny.messenger.utils.MasterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageBusinessImpl implements MessageBusiness
{
  private final MessageSimpleByRecipientService messageSimpleByRecipientService;
  private final MessageByTopicMessageIdService messageByTopicMessageIdService;
  private final MessageIdByRecipientTopicService messageIdByRecipientTopicService;

  private final MessageSimpleByRecipientMapper messageSimpleByRecipientMapper;
  private final MessageByTopicMessageIdMapper messageByTopicMessageIdMapper;
  private final MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper;

  private final RecipientByTopicService recipientByTopicService;

  public MessageBusinessImpl(MessageSimpleByRecipientService messageSimpleByRecipientService, MessageByTopicMessageIdService messageByTopicMessageIdService, MessageIdByRecipientTopicService messageIdByRecipientTopicService, MessageSimpleByRecipientMapper messageSimpleByRecipientMapper, MessageByTopicMessageIdMapper messageByTopicMessageIdMapper, MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper, RecipientByTopicService recipientByTopicService)
  {
    this.messageSimpleByRecipientService = messageSimpleByRecipientService;
    this.messageByTopicMessageIdService = messageByTopicMessageIdService;
    this.messageIdByRecipientTopicService = messageIdByRecipientTopicService;
    this.messageSimpleByRecipientMapper = messageSimpleByRecipientMapper;
    this.messageByTopicMessageIdMapper = messageByTopicMessageIdMapper;
    this.messageIdByRecipientTopicMapper = messageIdByRecipientTopicMapper;
    this.recipientByTopicService = recipientByTopicService;
  }


  @Override
  public Boolean createMessageSimple(UUID topicId)
  {
    List<RecipientByTopicDTO> recipientByTopicDTOs = recipientByTopicService.findByTopic(topicId);
    List<UUID> recipientIds = new ArrayList<>();
    MasterDTO master = MasterUtils.getMasterDefault();
    String messageContent;
    if (recipientByTopicDTOs.size() > 1)
    {
      messageContent = MessageDefault.FIRST_MESSAGE + MasterUtils.getMasterDefault().getUserName();
      for (RecipientByTopicDTO recipientByTopicDTO : recipientByTopicDTOs)
      {
        MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = new MessageSimpleByRecipientDTO();

        messageSimpleByRecipientDTO.setTopicId(topicId);
        messageSimpleByRecipientDTO.setRecipientId(recipientByTopicDTO.getRecipientId());
        messageSimpleByRecipientDTO.setContent(messageContent);
        messageSimpleByRecipientDTO.setCreateDate(Instant.now());
        messageSimpleByRecipientDTO.setMessageStatus("not seen");
        messageSimpleByRecipientDTO.setRecipientName(recipientByTopicDTO.getRecipientName());
        messageSimpleByRecipientDTO.setSenderId(master.getMasterId());
        messageSimpleByRecipientDTO.setSenderName(master.getUserName());
        messageSimpleByRecipientDTO.setTopicName(recipientByTopicDTO.getTopicName());

        messageSimpleByRecipientService.save(messageSimpleByRecipientDTO);
      }
    }
    else
      throw new NhechException();

    return true;
  }

  @Override
  public JSONObject updateMessageSimple(String sender, String messageContent)
  {
    return null;
  }
}
