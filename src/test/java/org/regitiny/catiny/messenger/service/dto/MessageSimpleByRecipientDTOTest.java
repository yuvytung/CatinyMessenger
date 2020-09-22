package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.web.rest.TestUtil;

import java.util.UUID;

public class MessageSimpleByRecipientDTOTest
{
  @Test
  public void dtoEqualsVerifier() throws Exception
  {

    TestUtil.equalsVerifier(MessageSimpleByRecipientDTO.class);
    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO1 = new MessageSimpleByRecipientDTO();
    messageSimpleByRecipientDTO1.setRecipientId(UUID.randomUUID());
    messageSimpleByRecipientDTO1.setTopicId(UUID.randomUUID());
    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO2 = new MessageSimpleByRecipientDTO();
    messageSimpleByRecipientDTO2.setRecipientId(messageSimpleByRecipientDTO1.getRecipientId());
    messageSimpleByRecipientDTO2.setTopicId(messageSimpleByRecipientDTO1.getTopicId());
    assertThat(messageSimpleByRecipientDTO1).isEqualTo(messageSimpleByRecipientDTO2);
    messageSimpleByRecipientDTO2.setRecipientId(UUID.randomUUID());
    messageSimpleByRecipientDTO2.setTopicId(UUID.randomUUID());
    assertThat(messageSimpleByRecipientDTO1).isNotEqualTo(messageSimpleByRecipientDTO2);
    messageSimpleByRecipientDTO1.setRecipientId(null);
    messageSimpleByRecipientDTO1.setTopicId(null);
    assertThat(messageSimpleByRecipientDTO1).isNotEqualTo(messageSimpleByRecipientDTO2);
  }
}
