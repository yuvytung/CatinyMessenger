package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import java.util.UUID;

public class RecipientByTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecipientByTopicDTO.class);
        RecipientByTopicDTO recipientByTopicDTO1 = new RecipientByTopicDTO();
        recipientByTopicDTO1.setId(UUID.randomUUID());
        RecipientByTopicDTO recipientByTopicDTO2 = new RecipientByTopicDTO();
        assertThat(recipientByTopicDTO1).isNotEqualTo(recipientByTopicDTO2);
        recipientByTopicDTO2.setId(recipientByTopicDTO1.getId());
        assertThat(recipientByTopicDTO1).isEqualTo(recipientByTopicDTO2);
        recipientByTopicDTO2.setId(UUID.randomUUID());
        assertThat(recipientByTopicDTO1).isNotEqualTo(recipientByTopicDTO2);
        recipientByTopicDTO1.setId(null);
        assertThat(recipientByTopicDTO1).isNotEqualTo(recipientByTopicDTO2);
    }
}
