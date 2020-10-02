package org.regitiny.catiny.messenger.service.dto;

import lombok.*;
import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;
import org.regitiny.tools.magic.quick.JsonQuickMagic;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageByTopicMessageId} entity.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageByTopicMessageIdDTO extends JsonQuickMagic implements Serializable {

    @NotNull
    private UUID topicId;

    @NotNull
    private Instant createDate;

    @NotNull
    private UUID messageId;

    private String senderName;

    private String messageContent;

    private Instant modifyDate;

    private String topicName;

    private UUID senderId;

    private Boolean deletedStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MessageByTopicMessageIdDTO))
            return false;
        return topicId != null && messageId != null && createDate != null &&
          topicId.equals(((MessageByTopicMessageIdDTO) o).topicId) &&
          createDate.equals(((MessageByTopicMessageIdDTO) o).createDate) &&
          messageId.equals(((MessageByTopicMessageIdDTO) o).messageId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageByTopicMessageIdDTO{" +
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
