package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.RecipientByTopicService;
import org.regitiny.catiny.messenger.domain.RecipientByTopic;
import org.regitiny.catiny.messenger.repository.RecipientByTopicRepository;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.RecipientByTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RecipientByTopic}.
 */
@Service
public class RecipientByTopicServiceImpl implements RecipientByTopicService {

    private final Logger log = LoggerFactory.getLogger(RecipientByTopicServiceImpl.class);

    private final RecipientByTopicRepository recipientByTopicRepository;

    private final RecipientByTopicMapper recipientByTopicMapper;

    public RecipientByTopicServiceImpl(RecipientByTopicRepository recipientByTopicRepository, RecipientByTopicMapper recipientByTopicMapper) {
        this.recipientByTopicRepository = recipientByTopicRepository;
        this.recipientByTopicMapper = recipientByTopicMapper;
    }

    @Override
    public RecipientByTopicDTO save(RecipientByTopicDTO recipientByTopicDTO) {
        log.debug("Request to save RecipientByTopic : {}", recipientByTopicDTO);
        RecipientByTopic recipientByTopic = recipientByTopicMapper.toEntity(recipientByTopicDTO);
        recipientByTopic = recipientByTopicRepository.save(recipientByTopic);
        return recipientByTopicMapper.toDto(recipientByTopic);
    }

    @Override
    public List<RecipientByTopicDTO> findAll() {
        log.debug("Request to get all RecipientByTopics");
        return recipientByTopicRepository.findAll().stream()
            .map(recipientByTopicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<RecipientByTopicDTO> findOne(UUID id) {
        log.debug("Request to get RecipientByTopic : {}", id);
        return recipientByTopicRepository.findById(id)
            .map(recipientByTopicMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete RecipientByTopic : {}", id);
        recipientByTopicRepository.deleteById(id);
    }
}
