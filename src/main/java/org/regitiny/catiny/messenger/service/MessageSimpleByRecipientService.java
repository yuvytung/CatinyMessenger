package org.regitiny.catiny.messenger.service;

import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient}.
 */
public interface MessageSimpleByRecipientService
{

  /**
   * Save a messageSimpleByRecipient.
   *
   * @param messageSimpleByRecipientDTO the entity to save.
   * @return the persisted entity.
   */
  MessageSimpleByRecipientDTO save(MessageSimpleByRecipientDTO messageSimpleByRecipientDTO);


  /**
   * Get all the messageSimpleByRecipients.
   *
   * @return the list of entities.
   */
  List<MessageSimpleByRecipientDTO> findAll();

  /**
   * Delete the messageSimpleByRecipient by primary key.
   *
   * @param recipientId and {@param topicId} is the primary key of the entity.
   */
  void delete(UUID recipientId, UUID topicId);


  /**
   * Get one messageSimpleByRecipient by primary key.
   *
   * @param recipientId is the partitionKey of the entity
   * @param topicId     is the clusterKey of the entity.
   * @return the entity.
   */
  MessageSimpleByRecipientDTO fetchOne(UUID recipientId, UUID topicId);


  /**
   * Get the messageSimpleByRecipients by recipientId.
   *
   * @param recipientId is the partition key of the entity.
   */
  List<MessageSimpleByRecipientDTO> findByRecipientId(UUID recipientId);

}
