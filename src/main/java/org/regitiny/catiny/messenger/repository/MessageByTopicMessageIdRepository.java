package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageByTopicMessageId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageByTopicMessageIdRepository extends CassandraRepository<MessageByTopicMessageId, UUID> {
}
