package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;

import java.time.Instant;
import java.util.UUID;

public class MessageByTopicMessageIdDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        Instant now = Instant.now();
        TestUtil.equalsVerifier(MessageByTopicMessageIdDTO.class);
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO1 = new MessageByTopicMessageIdDTO();
        messageByTopicMessageIdDTO1.setTopicId(UUID.randomUUID());
        messageByTopicMessageIdDTO1.setCreateDate(now);
        messageByTopicMessageIdDTO1.setMessageId(UUID.randomUUID());
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO2 = new MessageByTopicMessageIdDTO();
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO2.setTopicId(messageByTopicMessageIdDTO1.getTopicId());
        messageByTopicMessageIdDTO2.setCreateDate(messageByTopicMessageIdDTO1.getCreateDate());
        messageByTopicMessageIdDTO2.setMessageId(messageByTopicMessageIdDTO1.getMessageId());
        assertThat(messageByTopicMessageIdDTO1).isEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO2.setTopicId(UUID.randomUUID());
        messageByTopicMessageIdDTO2.setCreateDate(Instant.now());
        messageByTopicMessageIdDTO2.setMessageId(UUID.randomUUID());
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO1.setTopicId(null);
        messageByTopicMessageIdDTO1.setCreateDate(null);
        messageByTopicMessageIdDTO1.setMessageId(null);
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
    }
}
