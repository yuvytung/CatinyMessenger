package org.regitiny.catiny.messenger.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.regitiny.catiny.messenger.domain.key.MessageByTopicMessageIdKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A MessageByTopicMessageId.
 */
@Getter
@Setter
@NoArgsConstructor
@Table("messageByTopicMessageId")
public class MessageByTopicMessageId extends MessageByTopicMessageIdKey implements Serializable {

    private static final long serialVersionUID = 1L;

    private String senderName;

    private String messageContent;

    private Instant modifyDate;

    private String topicName;

    private UUID senderId;

    private Boolean deletedStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public MessageByTopicMessageId topicId(UUID topicId) {
        this.topicId = topicId;
        return this;
    }

    public MessageByTopicMessageId createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public MessageByTopicMessageId messageId(UUID messageId) {
        this.messageId = messageId;
        return this;
    }

    public MessageByTopicMessageId senderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public MessageByTopicMessageId messageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    public MessageByTopicMessageId modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public MessageByTopicMessageId topicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public MessageByTopicMessageId senderId(UUID senderId) {
        this.senderId = senderId;
        return this;
    }

    public MessageByTopicMessageId deletedStatus(Boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MessageByTopicMessageId))
            return false;
        return topicId != null && messageId != null && createDate != null &&
          topicId.equals(((MessageByTopicMessageId) o).topicId) &&
          createDate.equals(((MessageByTopicMessageId) o).createDate) &&
          messageId.equals(((MessageByTopicMessageId) o).messageId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageByTopicMessageId{" +
            "  topicId='" + getTopicId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", messageId='" + getMessageId() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", messageContent='" + getMessageContent() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", senderId='" + getSenderId() + "'" +
            ", deletedStatus='" + getDeletedStatus() + "'" +
            "}";
    }
}
