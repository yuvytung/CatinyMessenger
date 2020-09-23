package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class MessageByTopicMessageIdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageByTopicMessageId.class);
        MessageByTopicMessageId messageByTopicMessageId1 = new MessageByTopicMessageId();
        messageByTopicMessageId1.setId(UUID.randomUUID());
        MessageByTopicMessageId messageByTopicMessageId2 = new MessageByTopicMessageId();
        messageByTopicMessageId2.setId(messageByTopicMessageId1.getId());
        assertThat(messageByTopicMessageId1).isEqualTo(messageByTopicMessageId2);
        messageByTopicMessageId2.setId(UUID.randomUUID());
        assertThat(messageByTopicMessageId1).isNotEqualTo(messageByTopicMessageId2);
        messageByTopicMessageId1.setId(null);
        assertThat(messageByTopicMessageId1).isNotEqualTo(messageByTopicMessageId2);
    }
}
