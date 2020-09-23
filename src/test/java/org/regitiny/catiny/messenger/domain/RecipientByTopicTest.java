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
        recipientByTopic1.setId(UUID.randomUUID());
        RecipientByTopic recipientByTopic2 = new RecipientByTopic();
        recipientByTopic2.setId(recipientByTopic1.getId());
        assertThat(recipientByTopic1).isEqualTo(recipientByTopic2);
        recipientByTopic2.setId(UUID.randomUUID());
        assertThat(recipientByTopic1).isNotEqualTo(recipientByTopic2);
        recipientByTopic1.setId(null);
        assertThat(recipientByTopic1).isNotEqualTo(recipientByTopic2);
    }
}
