package org.regitiny.catiny.messenger.domain.key;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * A key of MessageIdByRecipientTopic.
 */

@Getter
@Setter
@NoArgsConstructor
public class RecipientByTopicKey
{
  @NotNull
  @PrimaryKeyColumn(name = "topicId", type = PrimaryKeyType.PARTITIONED , ordinal = 0)
  protected UUID topicId;

  @NotNull
  @PrimaryKeyColumn(name = "recipientId", ordinal = 1)
  protected UUID recipientId;
}
