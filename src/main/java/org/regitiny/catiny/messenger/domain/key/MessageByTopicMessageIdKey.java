package org.regitiny.catiny.messenger.domain.key;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * this is a key of MessageByTopicMessageId.
 */
@Getter
@Setter
@NoArgsConstructor
@Table("messageByTopicMessageId")
public class MessageByTopicMessageIdKey
{
  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED , ordinal = 0)
  protected UUID topicId;

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED , ordinal = 1 , ordering = Ordering.DESCENDING)
  protected Instant createDate;

  @NotNull
  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED , ordinal = 2)
  protected UUID messageId;
}
