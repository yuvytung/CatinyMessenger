package org.regitiny.catiny.messenger.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageByTopicMessageId.
 */
@Table("messageByTopicMessageId")
public class MessageByTopicMessageId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public MessageByTopicMessageId topicId(UUID topicId) {
        this.topicId = topicId;
        return this;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public MessageByTopicMessageId messageId(UUID messageId) {
        this.messageId = messageId;
        return this;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public MessageByTopicMessageId senderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public MessageByTopicMessageId messageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public MessageByTopicMessageId createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public MessageByTopicMessageId modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTopicName() {
        return topicName;
    }

    public MessageByTopicMessageId topicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public MessageByTopicMessageId senderId(UUID senderId) {
        this.senderId = senderId;
        return this;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public Boolean isDeletedStatus() {
        return deletedStatus;
    }

    public MessageByTopicMessageId deletedStatus(Boolean deletedStatus) {
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
        if (!(o instanceof MessageByTopicMessageId)) {
            return false;
        }
        return id != null && id.equals(((MessageByTopicMessageId) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageByTopicMessageId{" +
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
