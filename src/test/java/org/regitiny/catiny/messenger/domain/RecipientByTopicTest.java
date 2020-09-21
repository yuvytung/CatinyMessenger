package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class RecipientByTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecipientByTopic.class);
        RecipientByTopic recipientByTopic1 = new RecipientByTopic();
        recipientByTopic1.setTopicId(UUID.randomUUID());
        recipientByTopic1.setRecipientId(UUID.randomUUID());
        RecipientByTopic recipientByTopic2 = new RecipientByTopic();
        recipientByTopic2.setTopicId(recipientByTopic1.getTopicId());
        recipientByTopic2.setRecipientId(recipientByTopic1.getRecipientId());
        assertThat(recipientByTopic1).isEqualTo(recipientByTopic2);
        recipientByTopic2.setTopicId(UUID.randomUUID());
        recipientByTopic2.setRecipientId(UUID.randomUUID());
        assertThat(recipientByTopic1).isNotEqualTo(recipientByTopic2);
        recipientByTopic1.setTopicId(null);
        recipientByTopic1.setRecipientId(null);
        assertThat(recipientByTopic1).isNotEqualTo(recipientByTopic2);
    }
}
