package org.regitiny.catiny.messenger.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic} entity.
 */
public class MessageIdByRecipientTopicDTO implements Serializable {
    
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

    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UUID recipientId) {
        this.recipientId = recipientId;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Boolean isDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(Boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageIdByRecipientTopicDTO)) {
            return false;
        }

        return id != null && id.equals(((MessageIdByRecipientTopicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageIdByRecipientTopicDTO{" +
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
