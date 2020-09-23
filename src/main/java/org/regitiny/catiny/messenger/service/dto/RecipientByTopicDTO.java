package org.regitiny.catiny.messenger.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.catiny.messenger.domain.RecipientByTopic} entity.
 */
public class RecipientByTopicDTO implements Serializable {
    
    private UUID id;

    @NotNull
    private UUID topicId;

    @NotNull
    private UUID recipientId;

    private String role;

    private Instant createDate;

    private String recipientName;

    private String topicName;

    private UUID adderId;

    private UUID creatorId;

    
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

    public UUID getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(UUID recipientId) {
        this.recipientId = recipientId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
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

    public UUID getAdderId() {
        return adderId;
    }

    public void setAdderId(UUID adderId) {
        this.adderId = adderId;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecipientByTopicDTO)) {
            return false;
        }

        return id != null && id.equals(((RecipientByTopicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecipientByTopicDTO{" +
            "id=" + getId() +
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
