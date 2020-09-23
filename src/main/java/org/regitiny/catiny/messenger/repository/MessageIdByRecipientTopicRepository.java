package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageIdByRecipientTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageIdByRecipientTopicRepository extends CassandraRepository<MessageIdByRecipientTopic, UUID> {
}
