package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageByTopicMessageId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageByTopicMessageIdRepository extends CassandraRepository<MessageByTopicMessageId, UUID>
{
  MessageByTopicMessageId findOneByTopicIdAndCreateDateAndMessageId(UUID topicId, Instant createDate, UUID messageId);


  @AllowFiltering
  MessageByTopicMessageId findOneByTopicIdAndMessageId(UUID topicId, UUID messageId);


  Boolean deleteOneByTopicIdAndCreateDateAndMessageId(UUID topicId, Instant createDate, UUID messageId);
}
