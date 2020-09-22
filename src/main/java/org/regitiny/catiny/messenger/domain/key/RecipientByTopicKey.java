package org.regitiny.catiny.messenger.domain.key;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RecipientByTopicKey
{

  @NotNull
  @PrimaryKeyColumn(name = "topicId", type = PrimaryKeyType.PARTITIONED)
  protected UUID topicId;

  @NotNull
  @PrimaryKeyColumn(name = "recipientId", ordinal = 0)
  protected UUID recipientId;
}
