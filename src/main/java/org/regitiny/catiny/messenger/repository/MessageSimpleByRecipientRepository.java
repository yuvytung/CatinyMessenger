package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;

import org.regitiny.catiny.messenger.domain.key.MessageSimpleByRecipientKey;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageSimpleByRecipient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageSimpleByRecipientRepository extends CassandraRepository<MessageSimpleByRecipient, MessageSimpleByRecipientKey>
{
  List<MessageSimpleByRecipient> findByRecipientId(UUID recipientId);


  MessageSimpleByRecipient findOneByRecipientIdAndTopicId(UUID recipientId, UUID topicId);


  boolean deleteOneByRecipientIdAndTopicId(UUID recipientId, UUID topicId);


}
