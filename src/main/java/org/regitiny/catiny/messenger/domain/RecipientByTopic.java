package org.regitiny.catiny.messenger.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.regitiny.catiny.messenger.domain.key.RecipientByTopicKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A RecipientByTopic.
 * primary key of RecipientByTopic is ( (topicId) , recipientId )
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("recipientByTopic")
public class RecipientByTopic extends RecipientByTopicKey implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String role;

  private Instant createDate;

  private String recipientName;

  private String topicName;

  private UUID adderId;

  private UUID creatorId;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public RecipientByTopic topicId(UUID topicId)
  {
    this.topicId = topicId;
    return this;
  }

  public RecipientByTopic recipientId(UUID recipientId)
  {
    this.recipientId = recipientId;
    return this;
  }

  public RecipientByTopic role(String role)
  {
    this.role = role;
    return this;
  }

  public RecipientByTopic createDate(Instant createDate)
  {
    this.createDate = createDate;
    return this;
  }

  public RecipientByTopic recipientName(String recipientName)
  {
    this.recipientName = recipientName;
    return this;
  }

  public RecipientByTopic topicName(String topicName)
  {
    this.topicName = topicName;
    return this;
  }

  public RecipientByTopic adderId(UUID adderId)
  {
    this.adderId = adderId;
    return this;
  }

  public RecipientByTopic creatorId(UUID creatorId)
  {
    this.creatorId = creatorId;
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (!(o instanceof RecipientByTopic))
      return false;
    return (recipientId != null) &&
      (topicId != null) &&
      topicId.equals(((RecipientByTopic) o).topicId) &&
      recipientId.equals(((RecipientByTopic) o).recipientId);
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
    return "RecipientByTopic{" +
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
