package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic}.
 */
public interface MessageIdByRecipientTopicService {

    /**
     * Save a messageIdByRecipientTopic.
     *
     * @param messageIdByRecipientTopicDTO the entity to save.
     * @return the persisted entity.
     */
    MessageIdByRecipientTopicDTO save(MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO);

    /**
     * Get all the messageIdByRecipientTopics.
     *
     * @return the list of entities.
     */
    List<MessageIdByRecipientTopicDTO> findAll();


    /**
     * Get the "id" messageIdByRecipientTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MessageIdByRecipientTopicDTO> findOne(UUID id);

    /**
     * Delete the "id" messageIdByRecipientTopic.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
