package org.regitiny.catiny.messenger.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageByTopicMessageId} entity.
 */
public class MessageByTopicMessageIdDTO implements Serializable {
    
    private UUID id;

    @NotNull
    private UUID topicId;

    @NotNull
    private UUID messageId;

    private String senderName;

    private String messageContent;

    private Instant createDate;

    private Instant modifyDate;

    private String topicName;

    private UUID senderId;

    private Boolean deletedStatus;

    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
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
        if (!(o instanceof MessageByTopicMessageIdDTO)) {
            return false;
        }

        return id != null && id.equals(((MessageByTopicMessageIdDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageByTopicMessageIdDTO{" +
            "id=" + getId() +
            ", topicId='" + getTopicId() + "'" +
            ", messageId='" + getMessageId() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", messageContent='" + getMessageContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", senderId='" + getSenderId() + "'" +
            ", deletedStatus='" + isDeletedStatus() + "'" +
            "}";
    }
}
