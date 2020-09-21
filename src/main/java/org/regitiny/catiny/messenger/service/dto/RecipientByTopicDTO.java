package org.regitiny.catiny.messenger.service.dto;

import lombok.*;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.RecipientByTopic} entity.
 */
@Data
@NoArgsConstructor
public class RecipientByTopicDTO implements Serializable
{

  @NotNull
  private UUID topicId;

  @NotNull
  private UUID recipientId;

  private String role;

  private Instant createDate;

  private String recipientName;

  private String topicName;

  private UUID adderId;

  private UUID creatorId;
}
