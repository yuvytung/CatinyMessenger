package org.regitiny.catiny.messenger.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient} entity.
 */
public class MessageSimpleByRecipientDTO implements Serializable {
    
    private UUID id;

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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageSimpleByRecipientDTO)) {
            return false;
        }

        return id != null && id.equals(((MessageSimpleByRecipientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageSimpleByRecipientDTO{" +
            "id=" + getId() +
            ", recipientId='" + getRecipientId() + "'" +
            ", topicId='" + getTopicId() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", content='" + getContent() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", senderId='" + getSenderId() + "'" +
            ", recipientName='" + getRecipientName() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", messageStatus='" + getMessageStatus() + "'" +
            ", messageId='" + getMessageId() + "'" +
            "}";
    }
}
