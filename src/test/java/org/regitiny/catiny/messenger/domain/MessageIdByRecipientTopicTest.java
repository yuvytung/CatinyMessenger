package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class MessageIdByRecipientTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageIdByRecipientTopic.class);
        MessageIdByRecipientTopic messageIdByRecipientTopic1 = new MessageIdByRecipientTopic();
        messageIdByRecipientTopic1.setId(UUID.randomUUID());
        MessageIdByRecipientTopic messageIdByRecipientTopic2 = new MessageIdByRecipientTopic();
        messageIdByRecipientTopic2.setId(messageIdByRecipientTopic1.getId());
        assertThat(messageIdByRecipientTopic1).isEqualTo(messageIdByRecipientTopic2);
        messageIdByRecipientTopic2.setId(UUID.randomUUID());
        assertThat(messageIdByRecipientTopic1).isNotEqualTo(messageIdByRecipientTopic2);
        messageIdByRecipientTopic1.setId(null);
        assertThat(messageIdByRecipientTopic1).isNotEqualTo(messageIdByRecipientTopic2);
    }
}
