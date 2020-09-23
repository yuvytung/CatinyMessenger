package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;

import org.regitiny.catiny.messenger.domain.key.MessageIdByRecipientTopicKey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageIdByRecipientTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageIdByRecipientTopicRepository extends CassandraRepository<MessageIdByRecipientTopic, MessageIdByRecipientTopicKey>
{
  MessageIdByRecipientTopic findOneByRecipientIdAndTopicIdAndCreateDateAndMessageId(UUID recipientId, UUID topicId, Instant createDate, UUID messageId);


  @Query("select * " +
    "from messageIdByRecipientTopic " +
    "where recipientId = :recipientId " +
    "  and topicId = :topicId " +
    "  and createDate < :createDate " +
    "limit :limit ;")
  List<MessageIdByRecipientTopic> findByRecipientIdAndTopicIdAndCreateDateIsLessThan(UUID recipientId, UUID topicId, Instant createDate, int limit);


  List<MessageIdByRecipientTopic> findByRecipientIdAndTopicIdAndCreateDateIsLessThan(UUID recipientId, UUID topicId, Instant createDate);


  /**
   * createDate <=> messageId in one topic
   */
  @AllowFiltering
  MessageIdByRecipientTopic findByRecipientIdAndTopicIdAndMessageId(UUID recipientId, UUID topicId, UUID messageId);


  Boolean deleteOneByRecipientIdAndTopicIdAndCreateDateAndMessageId(UUID recipientId, UUID topicId, Instant createDate, UUID messageId);

}