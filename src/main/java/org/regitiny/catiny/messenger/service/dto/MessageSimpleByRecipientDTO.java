package org.regitiny.catiny.messenger.service.dto;

import com.google.gson.Gson;
import lombok.*;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient} entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSimpleByRecipientDTO extends JsonMagic implements Serializable
{

  @NotNull
  private UUID recipientId;

  @NotNull
  private UUID topicId;

  private String senderName;

  private String content;

  private Instant createDate;

  private UUID senderId;

  private String recipientName;

  private String topicName;

  private String messageStatus;

  private UUID messageId;

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (!(o instanceof MessageSimpleByRecipientDTO))
      return false;
    return (recipientId != null) &&
      (topicId != null) &&
      topicId.equals(((MessageSimpleByRecipientDTO) o).topicId) &&
      recipientId.equals(((MessageSimpleByRecipientDTO) o).recipientId);
  }

  @Override
  public int hashCode()
  {
    return 31;
  }

  @Override
  public String toString()
  {
    return "MessageSimpleByRecipientDTO{" +
      "recipientId=" + recipientId +
      ", topicId=" + topicId +
      ", senderName='" + senderName + '\'' +
      ", content='" + content + '\'' +
      ", createDate=" + createDate +
      ", senderId=" + senderId +
      ", recipientName='" + recipientName + '\'' +
      ", topicName='" + topicName + '\'' +
      ", messageStatus='" + messageStatus + '\'' +
      ", messageId=" + messageId +
      '}';
  }

}
