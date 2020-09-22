package org.regitiny.catiny.messenger.domain;

import lombok.ToString;
import org.regitiny.catiny.messenger.domain.key.MessageSimpleByRecipientKey;

import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageSimpleByRecipient.
 * primary key of MessageSimpleByRecipientKey ( (recipientId),topicId )
 */
@ToString
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

  public String getSenderName()
  {
    return senderName;
  }

  public MessageSimpleByRecipient senderName(String senderName)
  {
    this.senderName = senderName;
    return this;
  }

  public void setSenderName(String senderName)
  {
    this.senderName = senderName;
  }

  public String getContent()
  {
    return content;
  }

  public MessageSimpleByRecipient content(String content)
  {
    this.content = content;
    return this;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public Instant getCreateDate()
  {
    return createDate;
  }

  public MessageSimpleByRecipient createDate(Instant createDate)
  {
    this.createDate = createDate;
    return this;
  }

  public void setCreateDate(Instant createDate)
  {
    this.createDate = createDate;
  }

  public UUID getSenderId()
  {
    return senderId;
  }

  public MessageSimpleByRecipient senderId(UUID senderId)
  {
    this.senderId = senderId;
    return this;
  }

  public void setSenderId(UUID senderId)
  {
    this.senderId = senderId;
  }

  public String getRecipientName()
  {
    return recipientName;
  }

  public MessageSimpleByRecipient recipientName(String recipientName)
  {
    this.recipientName = recipientName;
    return this;
  }

  public void setRecipientName(String recipientName)
  {
    this.recipientName = recipientName;
  }

  public String getTopicName()
  {
    return topicName;
  }

  public MessageSimpleByRecipient topicName(String topicName)
  {
    this.topicName = topicName;
    return this;
  }

  public void setTopicName(String topicName)
  {
    this.topicName = topicName;
  }

  public String getMessageStatus()
  {
    return messageStatus;
  }

  public MessageSimpleByRecipient messageStatus(String messageStatus)
  {
    this.messageStatus = messageStatus;
    return this;
  }

  public void setMessageStatus(String messageStatus)
  {
    this.messageStatus = messageStatus;
  }

  public UUID getMessageId()
  {
    return messageId;
  }

  public MessageSimpleByRecipient messageId(UUID messageId)
  {
    this.messageId = messageId;
    return this;
  }

  public void setMessageId(UUID messageId)
  {
    this.messageId = messageId;
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
