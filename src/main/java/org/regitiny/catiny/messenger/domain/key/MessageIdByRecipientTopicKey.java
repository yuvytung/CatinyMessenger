package org.regitiny.catiny.messenger.domain.key;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

/**
 * A key of MessageIdByRecipientTopic
 */

@Getter
@Setter
@NoArgsConstructor
public class MessageIdByRecipientTopicKey
{

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED , ordinal = 0)
  protected UUID recipientId;

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED ,ordinal = 1)
  protected UUID topicId;

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 2 , ordering = Ordering.DESCENDING)
  protected Instant createDate;

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 3)
  protected UUID messageId;
}
