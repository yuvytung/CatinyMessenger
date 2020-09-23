package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.AbstractCassandraTest;
import org.regitiny.catiny.messenger.RedisTestContainerExtension;
import org.regitiny.catiny.messenger.CatinyMessengerApp;
import org.regitiny.catiny.messenger.config.SecurityBeanOverrideConfiguration;
import org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic;
import org.regitiny.catiny.messenger.repository.MessageIdByRecipientTopicRepository;
import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageIdByRecipientTopicMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MessageIdByRecipientTopicResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CatinyMessengerApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class MessageIdByRecipientTopicResourceIT extends AbstractCassandraTest {

    private static final UUID DEFAULT_RECIPIENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_RECIPIENT_ID = UUID.randomUUID();

    private static final UUID DEFAULT_TOPIC_ID = UUID.randomUUID();
    private static final UUID UPDATED_TOPIC_ID = UUID.randomUUID();

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final UUID DEFAULT_MESSAGE_ID = UUID.randomUUID();
    private static final UUID UPDATED_MESSAGE_ID = UUID.randomUUID();

    private static final String DEFAULT_MESSAGE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_STATUS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETED_STATUS = false;
    private static final Boolean UPDATED_DELETED_STATUS = true;

    @Autowired
    private MessageIdByRecipientTopicRepository messageIdByRecipientTopicRepository;

    @Autowired
    private MessageIdByRecipientTopicMapper messageIdByRecipientTopicMapper;

    @Autowired
    private MessageIdByRecipientTopicService messageIdByRecipientTopicService;

    @Autowired
    private MockMvc restMessageIdByRecipientTopicMockMvc;

    private MessageIdByRecipientTopic messageIdByRecipientTopic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageIdByRecipientTopic createEntity() {
        MessageIdByRecipientTopic messageIdByRecipientTopic = new MessageIdByRecipientTopic()
            .recipientId(DEFAULT_RECIPIENT_ID)
            .topicId(DEFAULT_TOPIC_ID)
            .createDate(DEFAULT_CREATE_DATE)
            .messageId(DEFAULT_MESSAGE_ID)
            .messageStatus(DEFAULT_MESSAGE_STATUS)
            .deletedStatus(DEFAULT_DELETED_STATUS);
        return messageIdByRecipientTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageIdByRecipientTopic createUpdatedEntity() {
        MessageIdByRecipientTopic messageIdByRecipientTopic = new MessageIdByRecipientTopic()
            .recipientId(UPDATED_RECIPIENT_ID)
            .topicId(UPDATED_TOPIC_ID)
            .createDate(UPDATED_CREATE_DATE)
            .messageId(UPDATED_MESSAGE_ID)
            .messageStatus(UPDATED_MESSAGE_STATUS)
            .deletedStatus(UPDATED_DELETED_STATUS);
        return messageIdByRecipientTopic;
    }

    @BeforeEach
    public void initTest() {
        messageIdByRecipientTopicRepository.deleteAll();
        messageIdByRecipientTopic = createEntity();
    }

    @Test
    public void createMessageIdByRecipientTopic() throws Exception {
        int databaseSizeBeforeCreate = messageIdByRecipientTopicRepository.findAll().size();
        // Create the MessageIdByRecipientTopic
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);
        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageIdByRecipientTopic in the database
        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeCreate + 1);
        MessageIdByRecipientTopic testMessageIdByRecipientTopic = messageIdByRecipientTopicList.get(messageIdByRecipientTopicList.size() - 1);
        assertThat(testMessageIdByRecipientTopic.getRecipientId()).isEqualTo(DEFAULT_RECIPIENT_ID);
        assertThat(testMessageIdByRecipientTopic.getTopicId()).isEqualTo(DEFAULT_TOPIC_ID);
        assertThat(testMessageIdByRecipientTopic.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMessageIdByRecipientTopic.getMessageId()).isEqualTo(DEFAULT_MESSAGE_ID);
        assertThat(testMessageIdByRecipientTopic.getMessageStatus()).isEqualTo(DEFAULT_MESSAGE_STATUS);
        assertThat(testMessageIdByRecipientTopic.isDeletedStatus()).isEqualTo(DEFAULT_DELETED_STATUS);
    }

    @Test
    public void createMessageIdByRecipientTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageIdByRecipientTopicRepository.findAll().size();

        // Create the MessageIdByRecipientTopic with an existing ID
        messageIdByRecipientTopic.setId(UUID.randomUUID());
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageIdByRecipientTopic in the database
        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkRecipientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageIdByRecipientTopicRepository.findAll().size();
        // set the field null
        messageIdByRecipientTopic.setRecipientId(null);

        // Create the MessageIdByRecipientTopic, which fails.
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);


        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTopicIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageIdByRecipientTopicRepository.findAll().size();
        // set the field null
        messageIdByRecipientTopic.setTopicId(null);

        // Create the MessageIdByRecipientTopic, which fails.
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);


        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageIdByRecipientTopicRepository.findAll().size();
        // set the field null
        messageIdByRecipientTopic.setCreateDate(null);

        // Create the MessageIdByRecipientTopic, which fails.
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);


        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMessageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageIdByRecipientTopicRepository.findAll().size();
        // set the field null
        messageIdByRecipientTopic.setMessageId(null);

        // Create the MessageIdByRecipientTopic, which fails.
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);


        restMessageIdByRecipientTopicMockMvc.perform(post("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMessageIdByRecipientTopics() throws Exception {
        // Initialize the database
        messageIdByRecipientTopic.setId(UUID.randomUUID());
        messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);

        // Get all the messageIdByRecipientTopicList
        restMessageIdByRecipientTopicMockMvc.perform(get("/api/message-id-by-recipient-topics"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageIdByRecipientTopic.getId().toString())))
            .andExpect(jsonPath("$.[*].recipientId").value(hasItem(DEFAULT_RECIPIENT_ID.toString())))
            .andExpect(jsonPath("$.[*].topicId").value(hasItem(DEFAULT_TOPIC_ID.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].messageId").value(hasItem(DEFAULT_MESSAGE_ID.toString())))
            .andExpect(jsonPath("$.[*].messageStatus").value(hasItem(DEFAULT_MESSAGE_STATUS)))
            .andExpect(jsonPath("$.[*].deletedStatus").value(hasItem(DEFAULT_DELETED_STATUS.booleanValue())));
    }
    
    @Test
    public void getMessageIdByRecipientTopic() throws Exception {
        // Initialize the database
        messageIdByRecipientTopic.setId(UUID.randomUUID());
        messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);

        // Get the messageIdByRecipientTopic
        restMessageIdByRecipientTopicMockMvc.perform(get("/api/message-id-by-recipient-topics/{id}", messageIdByRecipientTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messageIdByRecipientTopic.getId().toString()))
            .andExpect(jsonPath("$.recipientId").value(DEFAULT_RECIPIENT_ID.toString()))
            .andExpect(jsonPath("$.topicId").value(DEFAULT_TOPIC_ID.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.messageId").value(DEFAULT_MESSAGE_ID.toString()))
            .andExpect(jsonPath("$.messageStatus").value(DEFAULT_MESSAGE_STATUS))
            .andExpect(jsonPath("$.deletedStatus").value(DEFAULT_DELETED_STATUS.booleanValue()));
    }
    @Test
    public void getNonExistingMessageIdByRecipientTopic() throws Exception {
        // Get the messageIdByRecipientTopic
        restMessageIdByRecipientTopicMockMvc.perform(get("/api/message-id-by-recipient-topics/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMessageIdByRecipientTopic() throws Exception {
        // Initialize the database
        messageIdByRecipientTopic.setId(UUID.randomUUID());
        messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);

        int databaseSizeBeforeUpdate = messageIdByRecipientTopicRepository.findAll().size();

        // Update the messageIdByRecipientTopic
        MessageIdByRecipientTopic updatedMessageIdByRecipientTopic = messageIdByRecipientTopicRepository.findById(messageIdByRecipientTopic.getId()).get();
        updatedMessageIdByRecipientTopic
            .recipientId(UPDATED_RECIPIENT_ID)
            .topicId(UPDATED_TOPIC_ID)
            .createDate(UPDATED_CREATE_DATE)
            .messageId(UPDATED_MESSAGE_ID)
            .messageStatus(UPDATED_MESSAGE_STATUS)
            .deletedStatus(UPDATED_DELETED_STATUS);
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(updatedMessageIdByRecipientTopic);

        restMessageIdByRecipientTopicMockMvc.perform(put("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isOk());

        // Validate the MessageIdByRecipientTopic in the database
        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeUpdate);
        MessageIdByRecipientTopic testMessageIdByRecipientTopic = messageIdByRecipientTopicList.get(messageIdByRecipientTopicList.size() - 1);
        assertThat(testMessageIdByRecipientTopic.getRecipientId()).isEqualTo(UPDATED_RECIPIENT_ID);
        assertThat(testMessageIdByRecipientTopic.getTopicId()).isEqualTo(UPDATED_TOPIC_ID);
        assertThat(testMessageIdByRecipientTopic.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMessageIdByRecipientTopic.getMessageId()).isEqualTo(UPDATED_MESSAGE_ID);
        assertThat(testMessageIdByRecipientTopic.getMessageStatus()).isEqualTo(UPDATED_MESSAGE_STATUS);
        assertThat(testMessageIdByRecipientTopic.isDeletedStatus()).isEqualTo(UPDATED_DELETED_STATUS);
    }

    @Test
    public void updateNonExistingMessageIdByRecipientTopic() throws Exception {
        int databaseSizeBeforeUpdate = messageIdByRecipientTopicRepository.findAll().size();

        // Create the MessageIdByRecipientTopic
        MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO = messageIdByRecipientTopicMapper.toDto(messageIdByRecipientTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageIdByRecipientTopicMockMvc.perform(put("/api/message-id-by-recipient-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageIdByRecipientTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageIdByRecipientTopic in the database
        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMessageIdByRecipientTopic() throws Exception {
        // Initialize the database
        messageIdByRecipientTopic.setId(UUID.randomUUID());
        messageIdByRecipientTopicRepository.save(messageIdByRecipientTopic);

        int databaseSizeBeforeDelete = messageIdByRecipientTopicRepository.findAll().size();

        // Delete the messageIdByRecipientTopic
        restMessageIdByRecipientTopicMockMvc.perform(delete("/api/message-id-by-recipient-topics/{id}", messageIdByRecipientTopic.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MessageIdByRecipientTopic> messageIdByRecipientTopicList = messageIdByRecipientTopicRepository.findAll();
        assertThat(messageIdByRecipientTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
