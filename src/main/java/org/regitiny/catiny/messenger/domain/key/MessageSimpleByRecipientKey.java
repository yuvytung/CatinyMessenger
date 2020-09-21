package org.regitiny.catiny.messenger.domain.key;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageSimpleByRecipientKey
{
  @PrimaryKeyColumn(name = "recipientId", type = PrimaryKeyType.PARTITIONED)
  protected UUID recipientId;
  @PrimaryKeyColumn(name = "topicId", ordinal = 0)
  protected UUID topicId;
}
