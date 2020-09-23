package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.RecipientByTopic;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data Cassandra repository for the RecipientByTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecipientByTopicRepository extends CassandraRepository<RecipientByTopic, UUID>
{
  RecipientByTopic findOneByTopicIdAndRecipientId(UUID topicId, UUID recipientId);

  Boolean deleteOneByTopicIdAndRecipientId(UUID topicId,UUID recipientId);

  List<RecipientByTopic> findByTopicId(UUID topicId);

}
