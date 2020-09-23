package org.regitiny.catiny.messenger.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageIdByRecipientTopic.
 */
@Table("messageIdByRecipientTopic")
public class MessageIdByRecipientTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public MessageIdByRecipientTopic recipientId(UUID recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public void setRecipientId(UUID recipientId) {
        this.recipientId = recipientId;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public MessageIdByRecipientTopic topicId(UUID topicId) {
        this.topicId = topicId;
        return this;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public MessageIdByRecipientTopic createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public MessageIdByRecipientTopic messageId(UUID messageId) {
        this.messageId = messageId;
        return this;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public MessageIdByRecipientTopic messageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Boolean isDeletedStatus() {
        return deletedStatus;
    }

    public MessageIdByRecipientTopic deletedStatus(Boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
        return this;
    }

    public void setDeletedStatus(Boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageIdByRecipientTopic)) {
            return false;
        }
        return id != null && id.equals(((MessageIdByRecipientTopic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageIdByRecipientTopic{" +
            "id=" + getId() +
            ", recipientId='" + getRecipientId() + "'" +
            ", topicId='" + getTopicId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", messageId='" + getMessageId() + "'" +
            ", messageStatus='" + getMessageStatus() + "'" +
            ", deletedStatus='" + isDeletedStatus() + "'" +
            "}";
    }
}
