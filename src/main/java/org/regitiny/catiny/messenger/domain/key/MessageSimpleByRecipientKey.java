package org.regitiny.catiny.messenger.domain.key;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * A key of MessageSimpleByRecipient.
 */

@Getter
@Setter
@NoArgsConstructor
public class MessageSimpleByRecipientKey
{
  @NotNull
  @PrimaryKeyColumn( type = PrimaryKeyType.PARTITIONED , ordinal = 0)
  protected UUID recipientId;

  @NotNull
  @PrimaryKeyColumn( type = PrimaryKeyType.CLUSTERED , ordinal = 1)
  protected UUID topicId;
}
