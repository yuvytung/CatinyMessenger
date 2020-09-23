package org.regitiny.catiny.messenger.service.dto;

import lombok.*;

import org.regitiny.tools.magic.quick.JsonQuickMagic;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic} entity.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageIdByRecipientTopicDTO extends JsonQuickMagic implements Serializable
{

  @NotNull
  private UUID recipientId;

  @NotNull
  private UUID topicId;

  @NotNull
  private Instant createDate;

  @NotNull
  private UUID messageId;

  private String messageStatus;

  private Boolean deletedStatus;

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof MessageIdByRecipientTopicDTO))
    {
      return false;
    }
    return recipientId != null &&
      topicId != null &&
      messageId != null &&
      recipientId.equals(((MessageIdByRecipientTopicDTO) o).recipientId) &&
      topicId.equals(((MessageIdByRecipientTopicDTO) o).topicId) &&
      messageId.equals(((MessageIdByRecipientTopicDTO) o).messageId);
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
    return "MessageIdByRecipientTopicDTO{" +
      "  recipientId='" + getRecipientId() + "'" +
      ", topicId='" + getTopicId() + "'" +
      ", messageId='" + getMessageId() + "'" +
      ", createDate='" + getCreateDate() + "'" +
      ", messageStatus='" + getMessageStatus() + "'" +
      ", deletedStatus='" + getDeletedStatus() + "'" +
      "}";
  }
}
