package org.regitiny.catiny.messenger.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.RecipientByTopic} entity.
 */
@Getter
@Setter
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

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (!(o instanceof RecipientByTopicDTO))
      return false;
    return (recipientId != null) &&
      (topicId != null) &&
      topicId.equals(((RecipientByTopicDTO) o).topicId)&&
      recipientId.equals(((RecipientByTopicDTO) o).recipientId);
  }

  @Override
  public int hashCode()
  {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "RecipientByTopicDTO{" +
      ", topicId='" + getTopicId() + "'" +
      ", recipientId='" + getRecipientId() + "'" +
      ", role='" + getRole() + "'" +
      ", createDate='" + getCreateDate() + "'" +
      ", recipientName='" + getRecipientName() + "'" +
      ", topicName='" + getTopicName() + "'" +
      ", adderId='" + getAdderId() + "'" +
      ", creatorId='" + getCreatorId() + "'" +
      "}";
  }
}
