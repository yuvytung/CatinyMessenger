package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link org.regitiny.catiny.messenger.domain.RecipientByTopic}.
 */
public interface RecipientByTopicService
{

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
   * Get the recipientByTopic by primary key.
   *
   * @param topicId is the partition key of the entity.
   * @param recipientId is the cluster key of entity.
   * @return the entity.
   */
  Optional<RecipientByTopicDTO> fetchOne(UUID topicId , UUID recipientId);


  /**
   * Delete the recipientByTopic by primary key..
   *
   * @param topicId is the partition key of the entity.
   * @param recipientId is the cluster key of entity.
   * @return if return true is the deleted successful else false is the failure.
   */
  boolean delete(UUID topicId , UUID recipientId);


    /**
     * get recipient by topicId.
     *
     * @param topicId is the partition key of the entity.
     * @return if return true is the deleted successful else false is the failure.
     */
  List<RecipientByTopicDTO> findByTopic(UUID topicId);
}
