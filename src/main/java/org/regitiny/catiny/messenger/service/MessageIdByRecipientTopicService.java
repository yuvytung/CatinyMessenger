package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;

import java.time.Instant;
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
     * Get the messageIdByRecipientTopic by primary key.
     * recipientId , topicId and messageId is Required
     * if createDate = null this method will search according to the remaining three attributes
     * @param recipientId is partition key 1,
     * @param topicId is partition key 2,
     * @param createDate is cluster key 1,
     * @param messageId is cluster key 2,
     * @return all in entity.
     */
    MessageIdByRecipientTopicDTO fetchOne(UUID recipientId, UUID topicId, Instant createDate, UUID messageId);


    /**
     * Delete the messageIdByRecipientTopic by id.
     * recipientId , topicId and messageId is Required
     * if createDate = null this method will search according to the remaining three attributes
     * @param recipientId is partition key 1,
     * @param topicId is partition key 2,
     * @param createDate is cluster key 1,
     * @param messageId is cluster key 2,
     * @return the boolean.
     */
    Boolean delete(UUID recipientId, UUID topicId, Instant createDate, UUID messageId);


    /**
     *
     * @param recipientId is partition key 1,
     * @param topicId is partition key 2,
     * @param createDate is cluster key 1,
     * @param limit is limit number of result ,
     * @return the List of MessageIdByRecipientTopic
     */
    List<MessageIdByRecipientTopic> findByRecipientIdTopicIdCreateDateLessThan(UUID recipientId, UUID topicId, Instant createDate, Integer limit);
}
