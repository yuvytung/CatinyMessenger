package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.AbstractCassandraTest;
import org.regitiny.catiny.messenger.RedisTestContainerExtension;
import org.regitiny.catiny.messenger.CatinyMessengerApp;
import org.regitiny.catiny.messenger.config.SecurityBeanOverrideConfiguration;
import org.regitiny.catiny.messenger.domain.MessageByTopicMessageId;
import org.regitiny.catiny.messenger.repository.MessageByTopicMessageIdRepository;
import org.regitiny.catiny.messenger.service.MessageByTopicMessageIdService;
import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageByTopicMessageIdMapper;

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
 * Integration tests for the {@link MessageByTopicMessageIdResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CatinyMessengerApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class MessageByTopicMessageIdResourceIT extends AbstractCassandraTest {

    private static final UUID DEFAULT_TOPIC_ID = UUID.randomUUID();
    private static final UUID UPDATED_TOPIC_ID = UUID.randomUUID();

    private static final UUID DEFAULT_MESSAGE_ID = UUID.randomUUID();
    private static final UUID UPDATED_MESSAGE_ID = UUID.randomUUID();

    private static final String DEFAULT_SENDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_CONTENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TOPIC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TOPIC_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_SENDER_ID = UUID.randomUUID();
    private static final UUID UPDATED_SENDER_ID = UUID.randomUUID();

    private static final Boolean DEFAULT_DELETED_STATUS = false;
    private static final Boolean UPDATED_DELETED_STATUS = true;

    @Autowired
    private MessageByTopicMessageIdRepository messageByTopicMessageIdRepository;

    @Autowired
    private MessageByTopicMessageIdMapper messageByTopicMessageIdMapper;

    @Autowired
    private MessageByTopicMessageIdService messageByTopicMessageIdService;

    @Autowired
    private MockMvc restMessageByTopicMessageIdMockMvc;

    private MessageByTopicMessageId messageByTopicMessageId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageByTopicMessageId createEntity() {
        MessageByTopicMessageId messageByTopicMessageId = new MessageByTopicMessageId()
            .topicId(DEFAULT_TOPIC_ID)
            .messageId(DEFAULT_MESSAGE_ID)
            .senderName(DEFAULT_SENDER_NAME)
            .messageContent(DEFAULT_MESSAGE_CONTENT)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE)
            .topicName(DEFAULT_TOPIC_NAME)
            .senderId(DEFAULT_SENDER_ID)
            .deletedStatus(DEFAULT_DELETED_STATUS);
        return messageByTopicMessageId;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageByTopicMessageId createUpdatedEntity() {
        MessageByTopicMessageId messageByTopicMessageId = new MessageByTopicMessageId()
            .topicId(UPDATED_TOPIC_ID)
            .messageId(UPDATED_MESSAGE_ID)
            .senderName(UPDATED_SENDER_NAME)
            .messageContent(UPDATED_MESSAGE_CONTENT)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE)
            .topicName(UPDATED_TOPIC_NAME)
            .senderId(UPDATED_SENDER_ID)
            .deletedStatus(UPDATED_DELETED_STATUS);
        return messageByTopicMessageId;
    }

    @BeforeEach
    public void initTest() {
        messageByTopicMessageIdRepository.deleteAll();
        messageByTopicMessageId = createEntity();
    }

    @Test
    public void createMessageByTopicMessageId() throws Exception {
        int databaseSizeBeforeCreate = messageByTopicMessageIdRepository.findAll().size();
        // Create the MessageByTopicMessageId
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);
        restMessageByTopicMessageIdMockMvc.perform(post("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageByTopicMessageId in the database
        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeCreate + 1);
        MessageByTopicMessageId testMessageByTopicMessageId = messageByTopicMessageIdList.get(messageByTopicMessageIdList.size() - 1);
        assertThat(testMessageByTopicMessageId.getTopicId()).isEqualTo(DEFAULT_TOPIC_ID);
        assertThat(testMessageByTopicMessageId.getMessageId()).isEqualTo(DEFAULT_MESSAGE_ID);
        assertThat(testMessageByTopicMessageId.getSenderName()).isEqualTo(DEFAULT_SENDER_NAME);
        assertThat(testMessageByTopicMessageId.getMessageContent()).isEqualTo(DEFAULT_MESSAGE_CONTENT);
        assertThat(testMessageByTopicMessageId.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMessageByTopicMessageId.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
        assertThat(testMessageByTopicMessageId.getTopicName()).isEqualTo(DEFAULT_TOPIC_NAME);
        assertThat(testMessageByTopicMessageId.getSenderId()).isEqualTo(DEFAULT_SENDER_ID);
        assertThat(testMessageByTopicMessageId.isDeletedStatus()).isEqualTo(DEFAULT_DELETED_STATUS);
    }

    @Test
    public void createMessageByTopicMessageIdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageByTopicMessageIdRepository.findAll().size();

        // Create the MessageByTopicMessageId with an existing ID
        messageByTopicMessageId.setId(UUID.randomUUID());
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageByTopicMessageIdMockMvc.perform(post("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageByTopicMessageId in the database
        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTopicIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageByTopicMessageIdRepository.findAll().size();
        // set the field null
        messageByTopicMessageId.setTopicId(null);

        // Create the MessageByTopicMessageId, which fails.
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);


        restMessageByTopicMessageIdMockMvc.perform(post("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isBadRequest());

        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMessageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageByTopicMessageIdRepository.findAll().size();
        // set the field null
        messageByTopicMessageId.setMessageId(null);

        // Create the MessageByTopicMessageId, which fails.
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);


        restMessageByTopicMessageIdMockMvc.perform(post("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isBadRequest());

        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMessageByTopicMessageIds() throws Exception {
        // Initialize the database
        messageByTopicMessageId.setId(UUID.randomUUID());
        messageByTopicMessageIdRepository.save(messageByTopicMessageId);

        // Get all the messageByTopicMessageIdList
        restMessageByTopicMessageIdMockMvc.perform(get("/api/message-by-topic-message-ids"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageByTopicMessageId.getId().toString())))
            .andExpect(jsonPath("$.[*].topicId").value(hasItem(DEFAULT_TOPIC_ID.toString())))
            .andExpect(jsonPath("$.[*].messageId").value(hasItem(DEFAULT_MESSAGE_ID.toString())))
            .andExpect(jsonPath("$.[*].senderName").value(hasItem(DEFAULT_SENDER_NAME)))
            .andExpect(jsonPath("$.[*].messageContent").value(hasItem(DEFAULT_MESSAGE_CONTENT)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())))
            .andExpect(jsonPath("$.[*].topicName").value(hasItem(DEFAULT_TOPIC_NAME)))
            .andExpect(jsonPath("$.[*].senderId").value(hasItem(DEFAULT_SENDER_ID.toString())))
            .andExpect(jsonPath("$.[*].deletedStatus").value(hasItem(DEFAULT_DELETED_STATUS.booleanValue())));
    }
    
    @Test
    public void getMessageByTopicMessageId() throws Exception {
        // Initialize the database
        messageByTopicMessageId.setId(UUID.randomUUID());
        messageByTopicMessageIdRepository.save(messageByTopicMessageId);

        // Get the messageByTopicMessageId
        restMessageByTopicMessageIdMockMvc.perform(get("/api/message-by-topic-message-ids/{id}", messageByTopicMessageId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messageByTopicMessageId.getId().toString()))
            .andExpect(jsonPath("$.topicId").value(DEFAULT_TOPIC_ID.toString()))
            .andExpect(jsonPath("$.messageId").value(DEFAULT_MESSAGE_ID.toString()))
            .andExpect(jsonPath("$.senderName").value(DEFAULT_SENDER_NAME))
            .andExpect(jsonPath("$.messageContent").value(DEFAULT_MESSAGE_CONTENT))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()))
            .andExpect(jsonPath("$.topicName").value(DEFAULT_TOPIC_NAME))
            .andExpect(jsonPath("$.senderId").value(DEFAULT_SENDER_ID.toString()))
            .andExpect(jsonPath("$.deletedStatus").value(DEFAULT_DELETED_STATUS.booleanValue()));
    }
    @Test
    public void getNonExistingMessageByTopicMessageId() throws Exception {
        // Get the messageByTopicMessageId
        restMessageByTopicMessageIdMockMvc.perform(get("/api/message-by-topic-message-ids/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMessageByTopicMessageId() throws Exception {
        // Initialize the database
        messageByTopicMessageId.setId(UUID.randomUUID());
        messageByTopicMessageIdRepository.save(messageByTopicMessageId);

        int databaseSizeBeforeUpdate = messageByTopicMessageIdRepository.findAll().size();

        // Update the messageByTopicMessageId
        MessageByTopicMessageId updatedMessageByTopicMessageId = messageByTopicMessageIdRepository.findById(messageByTopicMessageId.getId()).get();
        updatedMessageByTopicMessageId
            .topicId(UPDATED_TOPIC_ID)
            .messageId(UPDATED_MESSAGE_ID)
            .senderName(UPDATED_SENDER_NAME)
            .messageContent(UPDATED_MESSAGE_CONTENT)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE)
            .topicName(UPDATED_TOPIC_NAME)
            .senderId(UPDATED_SENDER_ID)
            .deletedStatus(UPDATED_DELETED_STATUS);
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(updatedMessageByTopicMessageId);

        restMessageByTopicMessageIdMockMvc.perform(put("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isOk());

        // Validate the MessageByTopicMessageId in the database
        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeUpdate);
        MessageByTopicMessageId testMessageByTopicMessageId = messageByTopicMessageIdList.get(messageByTopicMessageIdList.size() - 1);
        assertThat(testMessageByTopicMessageId.getTopicId()).isEqualTo(UPDATED_TOPIC_ID);
        assertThat(testMessageByTopicMessageId.getMessageId()).isEqualTo(UPDATED_MESSAGE_ID);
        assertThat(testMessageByTopicMessageId.getSenderName()).isEqualTo(UPDATED_SENDER_NAME);
        assertThat(testMessageByTopicMessageId.getMessageContent()).isEqualTo(UPDATED_MESSAGE_CONTENT);
        assertThat(testMessageByTopicMessageId.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMessageByTopicMessageId.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
        assertThat(testMessageByTopicMessageId.getTopicName()).isEqualTo(UPDATED_TOPIC_NAME);
        assertThat(testMessageByTopicMessageId.getSenderId()).isEqualTo(UPDATED_SENDER_ID);
        assertThat(testMessageByTopicMessageId.isDeletedStatus()).isEqualTo(UPDATED_DELETED_STATUS);
    }

    @Test
    public void updateNonExistingMessageByTopicMessageId() throws Exception {
        int databaseSizeBeforeUpdate = messageByTopicMessageIdRepository.findAll().size();

        // Create the MessageByTopicMessageId
        MessageByTopicMessageIdDTO messageByTopicMessageIdDTO = messageByTopicMessageIdMapper.toDto(messageByTopicMessageId);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageByTopicMessageIdMockMvc.perform(put("/api/message-by-topic-message-ids").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(messageByTopicMessageIdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageByTopicMessageId in the database
        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMessageByTopicMessageId() throws Exception {
        // Initialize the database
        messageByTopicMessageId.setId(UUID.randomUUID());
        messageByTopicMessageIdRepository.save(messageByTopicMessageId);

        int databaseSizeBeforeDelete = messageByTopicMessageIdRepository.findAll().size();

        // Delete the messageByTopicMessageId
        restMessageByTopicMessageIdMockMvc.perform(delete("/api/message-by-topic-message-ids/{id}", messageByTopicMessageId.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MessageByTopicMessageId> messageByTopicMessageIdList = messageByTopicMessageIdRepository.findAll();
        assertThat(messageByTopicMessageIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
