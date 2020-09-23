package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class MessageByTopicMessageIdDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageByTopicMessageIdDTO.class);
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO1 = new MessageByTopicMessageIdDTO();
        messageByTopicMessageIdDTO1.setId(UUID.randomUUID());
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO2 = new MessageByTopicMessageIdDTO();
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO2.setId(messageByTopicMessageIdDTO1.getId());
        assertThat(messageByTopicMessageIdDTO1).isEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO2.setId(UUID.randomUUID());
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
        messageByTopicMessageIdDTO1.setId(null);
        assertThat(messageByTopicMessageIdDTO1).isNotEqualTo(messageByTopicMessageIdDTO2);
    }
}
