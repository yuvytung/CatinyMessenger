package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;

import java.time.Instant;
import java.util.UUID;

public class MessageIdByRecipientTopicTest {
    @Test
    public void equalsVerifier() throws Exception {
        Instant now = Instant.now();
        TestUtil.equalsVerifier(MessageIdByRecipientTopic.class);
        MessageIdByRecipientTopic messageIdByRecipientTopic1 = new MessageIdByRecipientTopic();
        messageIdByRecipientTopic1.setTopicId(UUID.randomUUID());
        messageIdByRecipientTopic1.setRecipientId(UUID.randomUUID());
        messageIdByRecipientTopic1.setCreateDate(now);
        messageIdByRecipientTopic1.setMessageId(UUID.randomUUID());
        MessageIdByRecipientTopic messageIdByRecipientTopic2 = new MessageIdByRecipientTopic();
        messageIdByRecipientTopic2.setTopicId(messageIdByRecipientTopic1.getTopicId());
        messageIdByRecipientTopic2.setRecipientId(messageIdByRecipientTopic1.getRecipientId());
        messageIdByRecipientTopic2.setCreateDate(messageIdByRecipientTopic1.getCreateDate());
        messageIdByRecipientTopic2.setMessageId(messageIdByRecipientTopic1.getMessageId());
        assertThat(messageIdByRecipientTopic1).isEqualTo(messageIdByRecipientTopic2);
        messageIdByRecipientTopic2.setTopicId(UUID.randomUUID());
        messageIdByRecipientTopic2.setRecipientId(UUID.randomUUID());
        messageIdByRecipientTopic2.setCreateDate(Instant.now());
        messageIdByRecipientTopic2.setMessageId(UUID.randomUUID());
        assertThat(messageIdByRecipientTopic1).isNotEqualTo(messageIdByRecipientTopic2);
        messageIdByRecipientTopic1.setTopicId(null);
        messageIdByRecipientTopic1.setRecipientId(null);
        messageIdByRecipientTopic1.setCreateDate(null);
        messageIdByRecipientTopic1.setMessageId(null);
        assertThat(messageIdByRecipientTopic1).isNotEqualTo(messageIdByRecipientTopic2);
    }
}
