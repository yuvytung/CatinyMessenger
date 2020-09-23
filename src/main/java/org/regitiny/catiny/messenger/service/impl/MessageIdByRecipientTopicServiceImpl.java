package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.repository.MessageIdByRecipientTopicRepository;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageIdByRecipientTopic}.
 */
@Service
public class MessageIdByRecipientTopicServiceImpl implements MessageIdByRecipientTopicService
{

  private final Logger log = LoggerFactory.getLogger(MessageIdByRecipientTopicServiceImpl.class);

  private final MessageIdByRecipientTopicRepository messageIdByRecipientTopicRepository;

  private final MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper;

  public MessageIdByRecipientTopicServiceImpl(MessageIdByRecipientTopicRepository messageIdByRecipientTopicRepository, MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper)
  {
    this.messageIdByRecipientTopicRepository = messageIdByRecipientTopicRepository;
    this.messageIdByRecipientTopicMapper = messageIdByRecipientTopicMapper;
  }

  @Override
  public MessageIdByRecipientTopicDTO save(MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO)
  {
    log.debug("Request to save MessageIdByRecipientTopic : {}", messageIdByRecipientTopicDTO);
    MessageIdByRecipientTopic messageIdByRecipientTopic = messageIdByRecipientTopicMapper.toEntity(messageIdByRecipientTopicDTO);
    messageIdByRecipientTopic = messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);
    return messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);
  }

  @Override
  public List<MessageIdByRecipientTopicDTO> findAll()
  {
    log.debug("Request to get all MessageIdByRecipientTopics");
    return messageIdByRecipientTopicRepository.findAll().stream()
      .map(messageIdByRecipientTopicMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }


  @Override
  public MessageIdByRecipientTopicDTO fetchOne(UUID recipientId, UUID topicId, Instant createDate, UUID messageId) throws NullPointerException
  {
    log.debug("Request to get MessageIdByRecipientTopic by primary key . recipientId = {} , topicId = {} , createDate = {} , messageId = {} ", recipientId, topicId, createDate, messageId);
    if (Objects.isNull(recipientId) || Objects.isNull(topicId) || Objects.isNull(messageId))
      throw new NullPointerException("recipientId, topicId, UUID messageId must require");

    if (Objects.isNull(createDate))
      return messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopicRepository.findByRecipientIdAndTopicIdAndMessageId(recipientId,topicId,messageId));
    return messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopicRepository.findOneByRecipientIdAndTopicIdAndCreateDateAndMessageId(recipientId, topicId, createDate, messageId));


  }

  @Override
  public Boolean delete(UUID recipientId, UUID topicId, Instant createDate, UUID messageId)
  {
    log.debug("Request to delete MessageIdByRecipientTopic by primary key . recipientId = {} , topicId = {} , createDate = {} , messageId = {} ", recipientId, topicId, createDate, messageId);

    if (Objects.isNull(recipientId) || Objects.isNull(topicId) || Objects.isNull(messageId))
      throw new NullPointerException("recipientId, topicId, UUID messageId must require");
    if (Objects.isNull(createDate))
    {
      MessageIdByRecipientTopic m = messageIdByRecipientTopicRepository.findByRecipientIdAndTopicIdAndMessageId(recipientId,topicId,messageId);
      if(Objects.nonNull(m))
        createDate = m.getCreateDate();
    }
    if (Objects.nonNull(createDate))
      return messageIdByRecipientTopicRepository.deleteOneByRecipientIdAndTopicIdAndCreateDateAndMessageId(recipientId, topicId, createDate, messageId);
    return false;
  }

  @Override
  public List<MessageIdByRecipientTopic> findByRecipientIdTopicIdCreateDateLessThan(UUID recipientId, UUID topicId, Instant createDate, Integer limit)
  {
    if (Objects.isNull(limit))
      limit = 10;
    return messageIdByRecipientTopicRepository.findByRecipientIdAndTopicIdAndCreateDateIsLessThan(recipientId, topicId, createDate, limit);
  }
}
