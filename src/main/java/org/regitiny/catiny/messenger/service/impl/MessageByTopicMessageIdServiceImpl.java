package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MessageByTopicMessageIdService;
import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;
import org.regitiny.catiny.messenger.repository.MessageByTopicMessageIdRepository;
import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageByTopicMessageIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageByTopicMessageId}.
 */
@Service
public class MessageByTopicMessageIdServiceImpl implements MessageByTopicMessageIdService {

    private final Logger log = LoggerFactory.getLogger(MessageByTopicMessageIdServiceImpl.class);

    private final MessageByTopicMessageIdRepository messageByTopicMessageIdRepository;

    private final MessageByTopicMessageIdMapper messageByTopicMessageIdMapper;

    public MessageByTopicMessageIdServiceImpl(MessageByTopicMessageIdRepository messageByTopicMessageIdRepository, MessageByTopicMessageIdMapper messageByTopicMessageIdMapper) {
        this.messageByTopicMessageIdRepository = messageByTopicMessageIdRepository;
        this.messageByTopicMessageIdMapper = messageByTopicMessageIdMapper;
    }

    @Override
    public MessageByTopicMessageIdDTO save(MessageByTopicMessageIdDTO messageByTopicMessageIdDTO) {
        log.debug("Request to save MessageByTopicMessageId : {}", messageByTopicMessageIdDTO);
        MessageByTopicMessageId messageByTopicMessageId = messageByTopicMessageIdMapper.toEntity(messageByTopicMessageIdDTO);
        messageByTopicMessageId = messageByTopicMessageIdRepository.save(messageByTopicMessageId);
        return messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);
    }

    @Override
    public List<MessageByTopicMessageIdDTO> findAll() {
        log.debug("Request to get all MessageByTopicMessageIds");
        return messageByTopicMessageIdRepository.findAll().stream()
            .map(messageByTopicMessageIdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<MessageByTopicMessageIdDTO> findOne(UUID id) {
        log.debug("Request to get MessageByTopicMessageId : {}", id);
        return messageByTopicMessageIdRepository.findById(id)
            .map(messageByTopicMessageIdMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete MessageByTopicMessageId : {}", id);
        messageByTopicMessageIdRepository.deleteById(id);
    }
}
