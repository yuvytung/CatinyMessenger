package org.regitiny.catiny.messenger.domain;

import lombok.*;
import org.regitiny.catiny.messenger.domain.key.MessageSimpleByRecipientKey;

import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageSimpleByRecipient.
 * primary key of MessageSimpleByRecipientKey ( (recipientId),topicId )
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("messageSimpleByRecipient")
public class MessageSimpleByRecipient extends MessageSimpleByRecipientKey implements Serializable
{

  private static final long serialVersionUID = 1L;

  private String senderName;

  private String content;

  private Instant createDate;

  private UUID senderId;

  private String recipientName;

  private String topicName;

  private String messageStatus;

  private UUID messageId;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public MessageSimpleByRecipient recipientId(UUID recipientId)
  {
    this.recipientId = recipientId;
    return this;
  }

  public MessageSimpleByRecipient topicId(UUID topicId)
  {
    this.topicId = topicId;
    return this;
  }

  public MessageSimpleByRecipient senderName(String senderName)
  {
    this.senderName = senderName;
    return this;
  }

  public MessageSimpleByRecipient content(String content)
  {
    this.content = content;
    return this;
  }

  public MessageSimpleByRecipient createDate(Instant createDate)
  {
    this.createDate = createDate;
    return this;
  }

  public MessageSimpleByRecipient senderId(UUID senderId)
  {
    this.senderId = senderId;
    return this;
  }

  public MessageSimpleByRecipient recipientName(String recipientName)
  {
    this.recipientName = recipientName;
    return this;
  }

  public MessageSimpleByRecipient topicName(String topicName)
  {
    this.topicName = topicName;
    return this;
  }

  public MessageSimpleByRecipient messageStatus(String messageStatus)
  {
    this.messageStatus = messageStatus;
    return this;
  }

  public MessageSimpleByRecipient messageId(UUID messageId)
  {
    this.messageId = messageId;
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (!(o instanceof MessageSimpleByRecipient))
      return false;
    return (recipientId != null) &&
      (topicId != null) &&
      recipientId.equals(((MessageSimpleByRecipient) o).recipientId) &&
      topicId.equals(((MessageSimpleByRecipient) o).topicId);
  }

  @Override
  public int hashCode()
  {
    return 31;
  }

}
