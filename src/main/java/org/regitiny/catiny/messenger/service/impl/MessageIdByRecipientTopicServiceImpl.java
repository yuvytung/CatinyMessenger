package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.repository.MessageIdByRecipientTopicRepository;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageIdByRecipientTopic}.
 */
@Service
public class MessageIdByRecipientTopicServiceImpl implements MessageIdByRecipientTopicService {

    private final Logger log = LoggerFactory.getLogger(MessageIdByRecipientTopicServiceImpl.class);

    private final MessageIdByRecipientTopicRepository messageIdByRecipientTopicRepository;

    private final MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper;

    public MessageIdByRecipientTopicServiceImpl(MessageIdByRecipientTopicRepository messageIdByRecipientTopicRepository, MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper) {
        this.messageIdByRecipientTopicRepository = messageIdByRecipientTopicRepository;
        this.messageIdByRecipientTopicMapper = messageIdByRecipientTopicMapper;
    }

    @Override
    public MessageIdByRecipientTopicDTO save(MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO) {
        log.debug("Request to save MessageIdByRecipientTopic : {}", messageIdByRecipientTopicDTO);
        MessageIdByRecipientTopic messageIdByRecipientTopic = messageIdByRecipientTopicMapper.toEntity(messageIdByRecipientTopicDTO);
        messageIdByRecipientTopic = messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);
        return messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);
    }

    @Override
    public List<MessageIdByRecipientTopicDTO> findAll() {
        log.debug("Request to get all MessageIdByRecipientTopics");
        return messageIdByRecipientTopicRepository.findAll().stream()
            .map(messageIdByRecipientTopicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<MessageIdByRecipientTopicDTO> findOne(UUID id) {
        log.debug("Request to get MessageIdByRecipientTopic : {}", id);
        return messageIdByRecipientTopicRepository.findById(id)
            .map(messageIdByRecipientTopicMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete MessageIdByRecipientTopic : {}", id);
        messageIdByRecipientTopicRepository.deleteById(id);
    }
}
