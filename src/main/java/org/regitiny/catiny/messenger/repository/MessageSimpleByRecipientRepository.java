package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the MessageSimpleByRecipient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageSimpleByRecipientRepository extends CassandraRepository<MessageSimpleByRecipient, UUID> {
}
