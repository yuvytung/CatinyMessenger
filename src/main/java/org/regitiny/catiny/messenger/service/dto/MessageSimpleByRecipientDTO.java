package org.regitiny.catiny.messenger.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient} entity.
 */
@Data
public class MessageSimpleByRecipientDTO implements Serializable {

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

    @Override
    public int hashCode() {
        return 31;
    }

}
