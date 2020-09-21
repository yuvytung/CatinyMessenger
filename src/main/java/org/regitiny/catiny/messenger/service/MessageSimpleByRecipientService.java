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
   * Get the "id" messageSimpleByRecipient.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
//    Optional<MessageSimpleByRecipientDTO> findOne(UUID id);


  /**
   * Delete the "id" messageSimpleByRecipient.
   *
   * @param recipientId the id of the entity.
   * @param topicId the id of the entity.
   */
  void delete(UUID recipientId, UUID topicId);


  /**
   * Get the "id" messageSimpleByRecipient.
   *
   * @param recipientId the partitionKey of the entity
   * @param topicId     the clusterKey of the entity.
   * @return the entity.
   */
  MessageSimpleByRecipientDTO fetchOne(UUID recipientId, UUID topicId);


  List<MessageSimpleByRecipientDTO> findByRecipientId(UUID recipientId);

}
