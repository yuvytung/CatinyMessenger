package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class MessageIdByRecipientTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageIdByRecipientTopicDTO.class);
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO1 = new MessageIdByRecipientTopicDTO();
        messageIdByRecipientTopicDTO1.setId(UUID.randomUUID());
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO2 = new MessageIdByRecipientTopicDTO();
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO2.setId(messageIdByRecipientTopicDTO1.getId());
        assertThat(messageIdByRecipientTopicDTO1).isEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO2.setId(UUID.randomUUID());
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO1.setId(null);
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);
    }
}
