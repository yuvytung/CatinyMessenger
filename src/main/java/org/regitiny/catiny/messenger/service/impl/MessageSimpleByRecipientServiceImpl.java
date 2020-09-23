package org.regitiny.catiny.messenger.service.impl;

import org.regitiny.catiny.messenger.service.MessageSimpleByRecipientService;
import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.repository.MessageSimpleByRecipientRepository;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageSimpleByRecipientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageSimpleByRecipient}.
 */
@Service
public class MessageSimpleByRecipientServiceImpl implements MessageSimpleByRecipientService {

    private final Logger log = LoggerFactory.getLogger(MessageSimpleByRecipientServiceImpl.class);

    private final MessageSimpleByRecipientRepository messageSimpleByRecipientRepository;

    private final MessageSimpleByRecipientMapper messageSimpleByRecipientMapper;

    public MessageSimpleByRecipientServiceImpl(MessageSimpleByRecipientRepository messageSimpleByRecipientRepository, MessageSimpleByRecipientMapper messageSimpleByRecipientMapper) {
        this.messageSimpleByRecipientRepository = messageSimpleByRecipientRepository;
        this.messageSimpleByRecipientMapper = messageSimpleByRecipientMapper;
    }

    @Override
    public MessageSimpleByRecipientDTO save(MessageSimpleByRecipientDTO messageSimpleByRecipientDTO) {
        log.debug("Request to save MessageSimpleByRecipient : {}", messageSimpleByRecipientDTO);
        MessageSimpleByRecipient messageSimpleByRecipient = messageSimpleByRecipientMapper.toEntity(messageSimpleByRecipientDTO);
        messageSimpleByRecipient = messageSimpleByRecipientRepository.save(messageSimpleByRecipient);
        return messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
    }

    @Override
    public List<MessageSimpleByRecipientDTO> findAll() {
        log.debug("Request to get all MessageSimpleByRecipients");
        return messageSimpleByRecipientRepository.findAll().stream()
            .map(messageSimpleByRecipientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public Optional<MessageSimpleByRecipientDTO> findOne(UUID id) {
        log.debug("Request to get MessageSimpleByRecipient : {}", id);
        return messageSimpleByRecipientRepository.findById(id)
            .map(messageSimpleByRecipientMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete MessageSimpleByRecipient : {}", id);
        messageSimpleByRecipientRepository.deleteById(id);
    }
}
