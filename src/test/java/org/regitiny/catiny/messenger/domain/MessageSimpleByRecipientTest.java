package org.regitiny.catiny.messenger.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.regitiny.catiny.messenger.web.rest.TestUtil;

import java.util.UUID;

public class MessageSimpleByRecipientTest
{
  @Test
  public void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(MessageSimpleByRecipient.class);
    MessageSimpleByRecipient messageSimpleByRecipient1 = new MessageSimpleByRecipient();
    messageSimpleByRecipient1.setRecipientId(UUID.randomUUID());
    messageSimpleByRecipient1.setTopicId(UUID.randomUUID());
    MessageSimpleByRecipient messageSimpleByRecipient2 = new MessageSimpleByRecipient();
    messageSimpleByRecipient2.setRecipientId(messageSimpleByRecipient1.getRecipientId());
    messageSimpleByRecipient2.setTopicId(messageSimpleByRecipient1.getTopicId());
    assertThat(messageSimpleByRecipient1).isEqualTo(messageSimpleByRecipient2);
    messageSimpleByRecipient2.setRecipientId(UUID.randomUUID());
    messageSimpleByRecipient2.setTopicId(UUID.randomUUID());
    assertThat(messageSimpleByRecipient1).isNotEqualTo(messageSimpleByRecipient2);
    messageSimpleByRecipient1.setRecipientId(null);
    messageSimpleByRecipient1.setTopicId(null);
    assertThat(messageSimpleByRecipient1).isNotEqualTo(messageSimpleByRecipient2);
  }
}
