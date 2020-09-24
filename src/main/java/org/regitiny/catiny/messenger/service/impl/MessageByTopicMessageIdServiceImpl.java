package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MessageByTopicMessageIdService;
import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;
import org.regitiny.catiny.messenger.repository.MessageByTopicMessageIdRepository;
import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageByTopicMessageIdMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageByTopicMessageId}.
 */
@Service
public class MessageByTopicMessageIdServiceImpl implements MessageByTopicMessageIdService
{

  private final Logger log = LoggerFactory.getLogger(MessageByTopicMessageIdServiceImpl.class);

  private final MessageByTopicMessageIdRepository messageByTopicMessageIdRepository;

  private final MessageByTopicMessageIdMapper messageByTopicMessageIdMapper;

  public MessageByTopicMessageIdServiceImpl(MessageByTopicMessageIdRepository messageByTopicMessageIdRepository, MessageByTopicMessageIdMapper messageByTopicMessageIdMapper)
  {
    this.messageByTopicMessageIdRepository = messageByTopicMessageIdRepository;
    this.messageByTopicMessageIdMapper = messageByTopicMessageIdMapper;
  }

  @Override
  public MessageByTopicMessageIdDTO save(MessageByTopicMessageIdDTO messageByTopicMessageIdDTO)
  {
    log.debug("Request to save MessageByTopicMessageId : {}", messageByTopicMessageIdDTO);
    MessageByTopicMessageId messageByTopicMessageId = messageByTopicMessageIdMapper.toEntity(messageByTopicMessageIdDTO);
    messageByTopicMessageId = messageByTopicMessageIdRepository.save(messageByTopicMessageId);
    return messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);
  }

  @Override
  public List<MessageByTopicMessageIdDTO> findAll()
  {
    log.debug("Request to get all MessageByTopicMessageIds");
    return messageByTopicMessageIdRepository.findAll().stream()
      .map(messageByTopicMessageIdMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }


  @Override
  public MessageByTopicMessageIdDTO fetchOne(UUID topicId, Instant createDate, UUID messageId)
  {
    log.debug("Request to get MessageByTopicMessageId . topicId = {} , createDate = {} , messageId = {}", topicId, createDate, messageId);
    if (Objects.isNull(topicId) || Objects.isNull(messageId))
      throw new NullPointerException("recipientId, topicId, UUID messageId must require");
    if (Objects.nonNull(createDate))
      return messageByTopicMessageIdMapper.toDto(messageByTopicMessageIdRepository.findOneByTopicIdAndCreateDateAndMessageId(topicId, createDate, messageId));
    return messageByTopicMessageIdMapper.toDto(messageByTopicMessageIdRepository.findOneByTopicIdAndMessageId(topicId, messageId));
  }

  @Override
  public Boolean delete(UUID topicId, Instant createDate, UUID messageId)
  {
    log.debug("Request to delete MessageByTopicMessageId . topicId = {} , createDate = {} , messageId = {}", topicId, createDate, messageId);
    if (Objects.isNull(topicId) || Objects.isNull(messageId))
      throw new NullPointerException("recipientId, topicId, UUID messageId must require");
    MessageByTopicMessageIdDTO result;

    if (Objects.nonNull(createDate))
      result = messageByTopicMessageIdMapper.toDto(messageByTopicMessageIdRepository.findOneByTopicIdAndCreateDateAndMessageId(topicId, createDate, messageId));
    else
      result = messageByTopicMessageIdMapper.toDto(messageByTopicMessageIdRepository.findOneByTopicIdAndMessageId(topicId, messageId));
    if (Objects.isNull(result))
      return false;
    messageByTopicMessageIdRepository.delete(messageByTopicMessageIdMapper.toEntity(result));
    return true;
  }
}
