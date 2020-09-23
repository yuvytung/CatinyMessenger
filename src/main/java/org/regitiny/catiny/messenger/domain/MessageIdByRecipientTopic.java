package org.regitiny.catiny.messenger.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.regitiny.catiny.messenger.domain.key.MessageIdByRecipientTopicKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageIdByRecipientTopic.
 */
@Getter
@Setter
@NoArgsConstructor
@Table("messageIdByRecipientTopic")
public class MessageIdByRecipientTopic extends MessageIdByRecipientTopicKey implements Serializable
{

  private static final long serialVersionUID = 1L;

  private String messageStatus;

  private Boolean deletedStatus;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public MessageIdByRecipientTopic recipientId(UUID recipientId)
  {
    this.recipientId = recipientId;
    return this;
  }

  public MessageIdByRecipientTopic topicId(UUID topicId)
  {
    this.topicId = topicId;
    return this;
  }

  public MessageIdByRecipientTopic createDate(Instant createDate)
  {
    this.createDate = createDate;
    return this;
  }

  public MessageIdByRecipientTopic messageId(UUID messageId)
  {
    this.messageId = messageId;
    return this;
  }

  public MessageIdByRecipientTopic messageStatus(String messageStatus)
  {
    this.messageStatus = messageStatus;
    return this;
  }

  public Boolean isDeletedStatus()
  {
    return deletedStatus;
  }

  public MessageIdByRecipientTopic deletedStatus(Boolean deletedStatus)
  {
    this.deletedStatus = deletedStatus;
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof MessageIdByRecipientTopic))
    {
      return false;
    }
    return recipientId != null &&
      topicId != null &&
      messageId != null &&
      recipientId.equals(((MessageIdByRecipientTopic) o).recipientId) &&
      topicId.equals(((MessageIdByRecipientTopic) o).topicId) &&
      messageId.equals(((MessageIdByRecipientTopic) o).messageId);
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
    return "MessageIdByRecipientTopic{" +
      "  recipientId='" + getRecipientId() + "'" +
      ", topicId='" + getTopicId() + "'" +
      ", messageId='" + getMessageId() + "'" +
      ", createDate='" + getCreateDate() + "'" +
      ", messageStatus='" + getMessageStatus() + "'" +
      ", deletedStatus='" + isDeletedStatus() + "'" +
      "}";
  }
}
