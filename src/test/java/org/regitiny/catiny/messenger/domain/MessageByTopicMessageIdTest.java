package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;

import java.time.Instant;
import java.util.UUID;

public class MessageByTopicMessageIdTest {

    @Test
    public void equalsVerifier() throws Exception {
        Instant now = Instant.now();
        TestUtil.equalsVerifier(MessageByTopicMessageId.class);
        MessageByTopicMessageId messageByTopicMessageId1 = new MessageByTopicMessageId();
        messageByTopicMessageId1.setTopicId(UUID.randomUUID());
        messageByTopicMessageId1.setCreateDate(now);
        messageByTopicMessageId1.setMessageId(UUID.randomUUID());
        MessageByTopicMessageId messageByTopicMessageId2 = new MessageByTopicMessageId();
        messageByTopicMessageId2.setTopicId(messageByTopicMessageId1.getTopicId());
        messageByTopicMessageId2.setCreateDate(messageByTopicMessageId1.getCreateDate());
        messageByTopicMessageId2.setMessageId(messageByTopicMessageId1.getMessageId());
        assertThat(messageByTopicMessageId1).isEqualTo(messageByTopicMessageId2);
        messageByTopicMessageId2.setTopicId(UUID.randomUUID());
        messageByTopicMessageId2.setCreateDate(Instant.now());
        messageByTopicMessageId2.setMessageId(UUID.randomUUID());
        assertThat(messageByTopicMessageId1).isNotEqualTo(messageByTopicMessageId2);
        messageByTopicMessageId1.setTopicId(null);
        messageByTopicMessageId1.setCreateDate(null);
        messageByTopicMessageId1.setMessageId(null);
        assertThat(messageByTopicMessageId1).isNotEqualTo(messageByTopicMessageId2);
    }
}
