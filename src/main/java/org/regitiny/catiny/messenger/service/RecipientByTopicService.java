package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link org.regitiny.catiny.messenger.domain.RecipientByTopic}.
 */
public interface RecipientByTopicService {

    /**
     * Save a recipientByTopic.
     *
     * @param recipientByTopicDTO the entity to save.
     * @return the persisted entity.
     */
    RecipientByTopicDTO save(RecipientByTopicDTO recipientByTopicDTO);

    /**
     * Get all the recipientByTopics.
     *
     * @return the list of entities.
     */
    List<RecipientByTopicDTO> findAll();


    /**
     * Get the "id" recipientByTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecipientByTopicDTO> findOne(UUID id);

    /**
     * Delete the "id" recipientByTopic.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
