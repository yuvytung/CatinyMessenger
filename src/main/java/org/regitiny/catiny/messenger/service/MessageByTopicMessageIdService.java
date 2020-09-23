package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link org.regitiny.catiny.messenger.domain.MessageByTopicMessageId}.
 */
public interface MessageByTopicMessageIdService {

    /**
     * Save a messageByTopicMessageId.
     *
     * @param messageByTopicMessageIdDTO the entity to save.
     * @return the persisted entity.
     */
    MessageByTopicMessageIdDTO save(MessageByTopicMessageIdDTO messageByTopicMessageIdDTO);

    /**
     * Get all the messageByTopicMessageIds.
     *
     * @return the list of entities.
     */
    List<MessageByTopicMessageIdDTO> findAll();


    /**
     * Get the "id" messageByTopicMessageId.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MessageByTopicMessageIdDTO> findOne(UUID id);

    /**
     * Delete the "id" messageByTopicMessageId.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
