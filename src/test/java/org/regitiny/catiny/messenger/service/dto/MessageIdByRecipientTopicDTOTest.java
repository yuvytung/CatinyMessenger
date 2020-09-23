package org.regitiny.catiny.messenger.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;
import org.regitiny.catiny.messenger.web.rest.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;
import org.json.JSONObject;

public class MessageIdByRecipientTopicDTOTest {

    @Autowired
    private MessageIdByRecipientTopicMapper mapper;
    @Autowired
    private MessageIdByRecipientTopicService service;

    @Test void testService() throws Exception
    {
        JSONObject jsonObject = new JSONObject();


        MessageIdByRecipientTopic messageIdByRecipientTopic = new MessageIdByRecipientTopic();
        messageIdByRecipientTopic.recipientId(UUID.randomUUID()).topicId(UUID.randomUUID()).createDate(Instant.now()).messageId(UUID.randomUUID());
        jsonObject.put("create object", messageIdByRecipientTopic );
        MessageIdByRecipientTopicDTO dto = service.save(mapper.toDto(messageIdByRecipientTopic));
        jsonObject.put("createResult", dto.toJsonObject());
        MessageIdByRecipientTopicDTO m2 = service.fetchOne(messageIdByRecipientTopic.getRecipientId(),messageIdByRecipientTopic.getTopicId(),messageIdByRecipientTopic.getCreateDate(),messageIdByRecipientTopic.getMessageId());
        jsonObject.put("find one", m2.toJsonObject());
        jsonObject.put("find all", service.findAll());

        jsonObject.put("deleteStatus", service.delete(messageIdByRecipientTopic.getRecipientId(),messageIdByRecipientTopic.getTopicId(),messageIdByRecipientTopic.getCreateDate(),messageIdByRecipientTopic.getMessageId()));

    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageIdByRecipientTopicDTO.class);
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO1 = new MessageIdByRecipientTopicDTO();
        messageIdByRecipientTopicDTO1.setRecipientId(UUID.randomUUID());
        messageIdByRecipientTopicDTO1.setTopicId(UUID.randomUUID());
        messageIdByRecipientTopicDTO1.setCreateDate(Instant.now());
        messageIdByRecipientTopicDTO1.setMessageId(UUID.randomUUID());
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO2 = new MessageIdByRecipientTopicDTO();
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO2.setRecipientId(messageIdByRecipientTopicDTO1.getRecipientId());
        messageIdByRecipientTopicDTO2.setTopicId(messageIdByRecipientTopicDTO1.getTopicId());
        messageIdByRecipientTopicDTO2.setCreateDate(messageIdByRecipientTopicDTO1.getCreateDate());
        messageIdByRecipientTopicDTO2.setMessageId(messageIdByRecipientTopicDTO1.getMessageId());
        assertThat(messageIdByRecipientTopicDTO1).isEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO2.setRecipientId(UUID.randomUUID());
        messageIdByRecipientTopicDTO2.setTopicId(UUID.randomUUID());
        messageIdByRecipientTopicDTO2.setCreateDate(Instant.now());
        messageIdByRecipientTopicDTO2.setMessageId(UUID.randomUUID());
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);
        messageIdByRecipientTopicDTO1.setRecipientId(null);
        messageIdByRecipientTopicDTO1.setTopicId(null);
        messageIdByRecipientTopicDTO1.setCreateDate(null);
        messageIdByRecipientTopicDTO1.setMessageId(null);
        assertThat(messageIdByRecipientTopicDTO1).isNotEqualTo(messageIdByRecipientTopicDTO2);

    }
}
