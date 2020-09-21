package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.AbstractCassandraTest;
import org.regitiny.catiny.messenger.RedisTestContainerExtension;
import org.regitiny.catiny.messenger.CatinyMessengerApp;
import org.regitiny.catiny.messenger.config.SecurityBeanOverrideConfiguration;
import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.repository.MessageSimpleByRecipientRepository;
import org.regitiny.catiny.messenger.service.MessageSimpleByRecipientService;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;
import org.regitiny.catiny.messenger.service.mapper.MessageSimpleByRecipientMapper;

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
 * Integration tests for the {@link MessageSimpleByRecipientResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, CatinyMessengerApp.class})
@ExtendWith({RedisTestContainerExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
@WithMockUser
public class MessageSimpleByRecipientResourceIT extends AbstractCassandraTest
{
//// TODO: 19-Sep-20
//  private static final UUID DEFAULT_RECIPIENT_ID = UUID.randomUUID();
//  private static final UUID UPDATED_RECIPIENT_ID = UUID.randomUUID();
//
//  private static final UUID DEFAULT_TOPIC_ID = UUID.randomUUID();
//  private static final UUID UPDATED_TOPIC_ID = UUID.randomUUID();
//
//  private static final String DEFAULT_SENDER_NAME = "AAAAAAAAAA";
//  private static final String UPDATED_SENDER_NAME = "BBBBBBBBBB";
//
//  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
//  private static final String UPDATED_CONTENT = "BBBBBBBBBB";
//
//  private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
//  private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//  private static final UUID DEFAULT_SENDER_ID = UUID.randomUUID();
//  private static final UUID UPDATED_SENDER_ID = UUID.randomUUID();
//
//  private static final String DEFAULT_RECIPIENT_NAME = "AAAAAAAAAA";
//  private static final String UPDATED_RECIPIENT_NAME = "BBBBBBBBBB";
//
//  private static final String DEFAULT_TOPIC_NAME = "AAAAAAAAAA";
//  private static final String UPDATED_TOPIC_NAME = "BBBBBBBBBB";
//
//  private static final String DEFAULT_MESSAGE_STATUS = "AAAAAAAAAA";
//  private static final String UPDATED_MESSAGE_STATUS = "BBBBBBBBBB";
//
//  private static final UUID DEFAULT_MESSAGE_ID = UUID.randomUUID();
//  private static final UUID UPDATED_MESSAGE_ID = UUID.randomUUID();
//
//  @Autowired
//  private MessageSimpleByRecipientRepository messageSimpleByRecipientRepository;
//
//  @Autowired
//  private MessageSimpleByRecipientMapper messageSimpleByRecipientMapper;
//
//  @Autowired
//  private MessageSimpleByRecipientService messageSimpleByRecipientService;
//
//  @Autowired
//  private MockMvc restMessageSimpleByRecipientMockMvc;
//
//  private MessageSimpleByRecipient messageSimpleByRecipient;
//
//  /**
//   * Create an entity for this test.
//   * <p>
//   * This is a static method, as tests for other entities might also need it,
//   * if they test an entity which requires the current entity.
//   */
//  public static MessageSimpleByRecipient createEntity()
//  {
//    MessageSimpleByRecipient messageSimpleByRecipient = new MessageSimpleByRecipient()
//      .recipientId(DEFAULT_RECIPIENT_ID)
//      .topicId(DEFAULT_TOPIC_ID)
//      .senderName(DEFAULT_SENDER_NAME)
//      .content(DEFAULT_CONTENT)
//      .createDate(DEFAULT_CREATE_DATE)
//      .senderId(DEFAULT_SENDER_ID)
//      .recipientName(DEFAULT_RECIPIENT_NAME)
//      .topicName(DEFAULT_TOPIC_NAME)
//      .messageStatus(DEFAULT_MESSAGE_STATUS)
//      .messageId(DEFAULT_MESSAGE_ID);
//    return messageSimpleByRecipient;
//  }
//
//  /**
//   * Create an updated entity for this test.
//   * <p>
//   * This is a static method, as tests for other entities might also need it,
//   * if they test an entity which requires the current entity.
//   */
//  public static MessageSimpleByRecipient createUpdatedEntity()
//  {
//    MessageSimpleByRecipient messageSimpleByRecipient = new MessageSimpleByRecipient()
//      .recipientId(UPDATED_RECIPIENT_ID)
//      .topicId(UPDATED_TOPIC_ID)
//      .senderName(UPDATED_SENDER_NAME)
//      .content(UPDATED_CONTENT)
//      .createDate(UPDATED_CREATE_DATE)
//      .senderId(UPDATED_SENDER_ID)
//      .recipientName(UPDATED_RECIPIENT_NAME)
//      .topicName(UPDATED_TOPIC_NAME)
//      .messageStatus(UPDATED_MESSAGE_STATUS)
//      .messageId(UPDATED_MESSAGE_ID);
//    return messageSimpleByRecipient;
//  }
//
//  @BeforeEach
//  public void initTest()
//  {
//    messageSimpleByRecipientRepository.deleteAll();
//    messageSimpleByRecipient = createEntity();
//  }
//
//  @Test
//  public void createMessageSimpleByRecipient() throws Exception
//  {
//    int databaseSizeBeforeCreate = messageSimpleByRecipientRepository.findAll().size();
//    // Create the MessageSimpleByRecipient
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
//    restMessageSimpleByRecipientMockMvc.perform(post("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isCreated());
//
//    // Validate the MessageSimpleByRecipient in the database
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeCreate + 1);
//    MessageSimpleByRecipient testMessageSimpleByRecipient = messageSimpleByRecipientList.get(messageSimpleByRecipientList.size() - 1);
//    assertThat(testMessageSimpleByRecipient.getRecipientId()).isEqualTo(DEFAULT_RECIPIENT_ID);
//    assertThat(testMessageSimpleByRecipient.getTopicId()).isEqualTo(DEFAULT_TOPIC_ID);
//    assertThat(testMessageSimpleByRecipient.getSenderName()).isEqualTo(DEFAULT_SENDER_NAME);
//    assertThat(testMessageSimpleByRecipient.getContent()).isEqualTo(DEFAULT_CONTENT);
//    assertThat(testMessageSimpleByRecipient.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
//    assertThat(testMessageSimpleByRecipient.getSenderId()).isEqualTo(DEFAULT_SENDER_ID);
//    assertThat(testMessageSimpleByRecipient.getRecipientName()).isEqualTo(DEFAULT_RECIPIENT_NAME);
//    assertThat(testMessageSimpleByRecipient.getTopicName()).isEqualTo(DEFAULT_TOPIC_NAME);
//    assertThat(testMessageSimpleByRecipient.getMessageStatus()).isEqualTo(DEFAULT_MESSAGE_STATUS);
//    assertThat(testMessageSimpleByRecipient.getMessageId()).isEqualTo(DEFAULT_MESSAGE_ID);
//  }
//
//  @Test
//  public void createMessageSimpleByRecipientWithExistingId() throws Exception
//  {
//    int databaseSizeBeforeCreate = messageSimpleByRecipientRepository.findAll().size();
//
//    // Create the MessageSimpleByRecipient with an existing ID
//    messageSimpleByRecipient.setId(UUID.randomUUID());
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
//
//    // An entity with an existing ID cannot be created, so this API call must fail
//    restMessageSimpleByRecipientMockMvc.perform(post("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isBadRequest());
//
//    // Validate the MessageSimpleByRecipient in the database
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeCreate);
//  }
//
//
//  @Test
//  public void checkRecipientIdIsRequired() throws Exception
//  {
//    int databaseSizeBeforeTest = messageSimpleByRecipientRepository.findAll().size();
//    // set the field null
//    messageSimpleByRecipient.setRecipientId(null);
//
//    // Create the MessageSimpleByRecipient, which fails.
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
//
//
//    restMessageSimpleByRecipientMockMvc.perform(post("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isBadRequest());
//
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeTest);
//  }
//
//  @Test
//  public void checkTopicIdIsRequired() throws Exception
//  {
//    int databaseSizeBeforeTest = messageSimpleByRecipientRepository.findAll().size();
//    // set the field null
//    messageSimpleByRecipient.setTopicId(null);
//
//    // Create the MessageSimpleByRecipient, which fails.
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
//
//
//    restMessageSimpleByRecipientMockMvc.perform(post("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isBadRequest());
//
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeTest);
//  }
//
//  @Test
//  public void getAllMessageSimpleByRecipients() throws Exception
//  {
//    // Initialize the database
//    messageSimpleByRecipient.setId(UUID.randomUUID());
//    messageSimpleByRecipientRepository.save(messageSimpleByRecipient);
//
//    // Get all the messageSimpleByRecipientList
//    restMessageSimpleByRecipientMockMvc.perform(get("/api/message-simple-by-recipients"))
//      .andExpect(status().isOk())
//      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//      .andExpect(jsonPath("$.[*].id").value(hasItem(messageSimpleByRecipient.getId().toString())))
//      .andExpect(jsonPath("$.[*].recipientId").value(hasItem(DEFAULT_RECIPIENT_ID.toString())))
//      .andExpect(jsonPath("$.[*].topicId").value(hasItem(DEFAULT_TOPIC_ID.toString())))
//      .andExpect(jsonPath("$.[*].senderName").value(hasItem(DEFAULT_SENDER_NAME)))
//      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT)))
//      .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
//      .andExpect(jsonPath("$.[*].senderId").value(hasItem(DEFAULT_SENDER_ID.toString())))
//      .andExpect(jsonPath("$.[*].recipientName").value(hasItem(DEFAULT_RECIPIENT_NAME)))
//      .andExpect(jsonPath("$.[*].topicName").value(hasItem(DEFAULT_TOPIC_NAME)))
//      .andExpect(jsonPath("$.[*].messageStatus").value(hasItem(DEFAULT_MESSAGE_STATUS)))
//      .andExpect(jsonPath("$.[*].messageId").value(hasItem(DEFAULT_MESSAGE_ID.toString())));
//  }
//
//  @Test
//  public void getMessageSimpleByRecipient() throws Exception
//  {
//    // Initialize the database
//    messageSimpleByRecipient.setId(UUID.randomUUID());
//    messageSimpleByRecipientRepository.save(messageSimpleByRecipient);
//
//    // Get the messageSimpleByRecipient
//    restMessageSimpleByRecipientMockMvc.perform(get("/api/message-simple-by-recipients/{id}", messageSimpleByRecipient.getId()))
//      .andExpect(status().isOk())
//      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//      .andExpect(jsonPath("$.id").value(messageSimpleByRecipient.getId().toString()))
//      .andExpect(jsonPath("$.recipientId").value(DEFAULT_RECIPIENT_ID.toString()))
//      .andExpect(jsonPath("$.topicId").value(DEFAULT_TOPIC_ID.toString()))
//      .andExpect(jsonPath("$.senderName").value(DEFAULT_SENDER_NAME))
//      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT))
//      .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
//      .andExpect(jsonPath("$.senderId").value(DEFAULT_SENDER_ID.toString()))
//      .andExpect(jsonPath("$.recipientName").value(DEFAULT_RECIPIENT_NAME))
//      .andExpect(jsonPath("$.topicName").value(DEFAULT_TOPIC_NAME))
//      .andExpect(jsonPath("$.messageStatus").value(DEFAULT_MESSAGE_STATUS))
//      .andExpect(jsonPath("$.messageId").value(DEFAULT_MESSAGE_ID.toString()));
//  }
//
//  @Test
//  public void getNonExistingMessageSimpleByRecipient() throws Exception
//  {
//    // Get the messageSimpleByRecipient
//    restMessageSimpleByRecipientMockMvc.perform(get("/api/message-simple-by-recipients/{id}", UUID.randomUUID().toString()))
//      .andExpect(status().isNotFound());
//  }
//
//  @Test
//  public void updateMessageSimpleByRecipient() throws Exception
//  {
//    // Initialize the database
//    messageSimpleByRecipient.setId(UUID.randomUUID());
//    messageSimpleByRecipientRepository.save(messageSimpleByRecipient);
//
//    int databaseSizeBeforeUpdate = messageSimpleByRecipientRepository.findAll().size();
//
//    // Update the messageSimpleByRecipient
//    MessageSimpleByRecipient updatedMessageSimpleByRecipient = messageSimpleByRecipientRepository.findById(messageSimpleByRecipient.getId()).get();
//    updatedMessageSimpleByRecipient
//      .recipientId(UPDATED_RECIPIENT_ID)
//      .topicId(UPDATED_TOPIC_ID)
//      .senderName(UPDATED_SENDER_NAME)
//      .content(UPDATED_CONTENT)
//      .createDate(UPDATED_CREATE_DATE)
//      .senderId(UPDATED_SENDER_ID)
//      .recipientName(UPDATED_RECIPIENT_NAME)
//      .topicName(UPDATED_TOPIC_NAME)
//      .messageStatus(UPDATED_MESSAGE_STATUS)
//      .messageId(UPDATED_MESSAGE_ID);
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(updatedMessageSimpleByRecipient);
//
//    restMessageSimpleByRecipientMockMvc.perform(put("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isOk());
//
//    // Validate the MessageSimpleByRecipient in the database
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeUpdate);
//    MessageSimpleByRecipient testMessageSimpleByRecipient = messageSimpleByRecipientList.get(messageSimpleByRecipientList.size() - 1);
//    assertThat(testMessageSimpleByRecipient.getRecipientId()).isEqualTo(UPDATED_RECIPIENT_ID);
//    assertThat(testMessageSimpleByRecipient.getTopicId()).isEqualTo(UPDATED_TOPIC_ID);
//    assertThat(testMessageSimpleByRecipient.getSenderName()).isEqualTo(UPDATED_SENDER_NAME);
//    assertThat(testMessageSimpleByRecipient.getContent()).isEqualTo(UPDATED_CONTENT);
//    assertThat(testMessageSimpleByRecipient.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//    assertThat(testMessageSimpleByRecipient.getSenderId()).isEqualTo(UPDATED_SENDER_ID);
//    assertThat(testMessageSimpleByRecipient.getRecipientName()).isEqualTo(UPDATED_RECIPIENT_NAME);
//    assertThat(testMessageSimpleByRecipient.getTopicName()).isEqualTo(UPDATED_TOPIC_NAME);
//    assertThat(testMessageSimpleByRecipient.getMessageStatus()).isEqualTo(UPDATED_MESSAGE_STATUS);
//    assertThat(testMessageSimpleByRecipient.getMessageId()).isEqualTo(UPDATED_MESSAGE_ID);
//  }
//
//  @Test
//  public void updateNonExistingMessageSimpleByRecipient() throws Exception
//  {
//    int databaseSizeBeforeUpdate = messageSimpleByRecipientRepository.findAll().size();
//
//    // Create the MessageSimpleByRecipient
//    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientMapper.toDto(messageSimpleByRecipient);
//
//    // If the entity doesn't have an ID, it will throw BadRequestAlertException
//    restMessageSimpleByRecipientMockMvc.perform(put("/api/message-simple-by-recipients").with(csrf())
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(TestUtil.convertObjectToJsonBytes(messageSimpleByRecipientDTO)))
//      .andExpect(status().isBadRequest());
//
//    // Validate the MessageSimpleByRecipient in the database
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeUpdate);
//  }
//
//  @Test
//  public void deleteMessageSimpleByRecipient() throws Exception
//  {
//    // Initialize the database
//    messageSimpleByRecipient.setId(UUID.randomUUID());
//    messageSimpleByRecipientRepository.save(messageSimpleByRecipient);
//
//    int databaseSizeBeforeDelete = messageSimpleByRecipientRepository.findAll().size();
//
//    // Delete the messageSimpleByRecipient
//    restMessageSimpleByRecipientMockMvc.perform(delete("/api/message-simple-by-recipients/{id}", messageSimpleByRecipient.getId()).with(csrf())
//      .accept(MediaType.APPLICATION_JSON))
//      .andExpect(status().isNoContent());
//
//    // Validate the database contains one less item
//    List<MessageSimpleByRecipient> messageSimpleByRecipientList = messageSimpleByRecipientRepository.findAll();
//    assertThat(messageSimpleByRecipientList).hasSize(databaseSizeBeforeDelete - 1);
//  }
}
