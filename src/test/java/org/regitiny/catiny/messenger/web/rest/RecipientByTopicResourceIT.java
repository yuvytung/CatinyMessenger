package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.AbstractCassandraTest;
import org.regitiny.catiny.messenger.RedisTestContainerExtension;
import org.regitiny.catiny.messenger.CatinyMessengerApp;
import org.regitiny.catiny.messenger.config.SecurityBeanOverrideConfiguration;
import org.regitiny.catiny.messenger.domain.RecipientByTopic;
import org.regitiny.catiny.messenger.repository.RecipientByTopicRepository;
import org.regitiny.catiny.messenger.service.RecipientByTopicService;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.RecipientByTopicMapper;

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
 * Integration tests for the {@link RecipientByTopicResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, CatinyMessengerApp.class })
@ExtendWith({ RedisTestContainerExtension.class, MockitoExtension.class })
@AutoConfigureMockMvc
@WithMockUser
public class RecipientByTopicResourceIT extends AbstractCassandraTest {

    private static final UUID DEFAULT_TOPIC_ID = UUID.randomUUID();
    private static final UUID UPDATED_TOPIC_ID = UUID.randomUUID();

    private static final UUID DEFAULT_RECIPIENT_ID = UUID.randomUUID();
    private static final UUID UPDATED_RECIPIENT_ID = UUID.randomUUID();

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RECIPIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECIPIENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TOPIC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TOPIC_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_ADDER_ID = UUID.randomUUID();
    private static final UUID UPDATED_ADDER_ID = UUID.randomUUID();

    private static final UUID DEFAULT_CREATOR_ID = UUID.randomUUID();
    private static final UUID UPDATED_CREATOR_ID = UUID.randomUUID();

    @Autowired
    private RecipientByTopicRepository recipientByTopicRepository;

    @Autowired
    private RecipientByTopicMapper recipientByTopicMapper;

    @Autowired
    private RecipientByTopicService recipientByTopicService;

    @Autowired
    private MockMvc restRecipientByTopicMockMvc;

    private RecipientByTopic recipientByTopic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecipientByTopic createEntity() {
        RecipientByTopic recipientByTopic = new RecipientByTopic()
            .topicId(DEFAULT_TOPIC_ID)
            .recipientId(DEFAULT_RECIPIENT_ID)
            .role(DEFAULT_ROLE)
            .createDate(DEFAULT_CREATE_DATE)
            .recipientName(DEFAULT_RECIPIENT_NAME)
            .topicName(DEFAULT_TOPIC_NAME)
            .adderId(DEFAULT_ADDER_ID)
            .creatorId(DEFAULT_CREATOR_ID);
        return recipientByTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecipientByTopic createUpdatedEntity() {
        RecipientByTopic recipientByTopic = new RecipientByTopic()
            .topicId(UPDATED_TOPIC_ID)
            .recipientId(UPDATED_RECIPIENT_ID)
            .role(UPDATED_ROLE)
            .createDate(UPDATED_CREATE_DATE)
            .recipientName(UPDATED_RECIPIENT_NAME)
            .topicName(UPDATED_TOPIC_NAME)
            .adderId(UPDATED_ADDER_ID)
            .creatorId(UPDATED_CREATOR_ID);
        return recipientByTopic;
    }

    @BeforeEach
    public void initTest() {
        recipientByTopicRepository.deleteAll();
        recipientByTopic = createEntity();
    }

    @Test
    public void createRecipientByTopic() throws Exception {
        int databaseSizeBeforeCreate = recipientByTopicRepository.findAll().size();
        // Create the RecipientByTopic
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(recipientByTopic);
        restRecipientByTopicMockMvc.perform(post("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the RecipientByTopic in the database
        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeCreate + 1);
        RecipientByTopic testRecipientByTopic = recipientByTopicList.get(recipientByTopicList.size() - 1);
        assertThat(testRecipientByTopic.getTopicId()).isEqualTo(DEFAULT_TOPIC_ID);
        assertThat(testRecipientByTopic.getRecipientId()).isEqualTo(DEFAULT_RECIPIENT_ID);
        assertThat(testRecipientByTopic.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testRecipientByTopic.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testRecipientByTopic.getRecipientName()).isEqualTo(DEFAULT_RECIPIENT_NAME);
        assertThat(testRecipientByTopic.getTopicName()).isEqualTo(DEFAULT_TOPIC_NAME);
        assertThat(testRecipientByTopic.getAdderId()).isEqualTo(DEFAULT_ADDER_ID);
        assertThat(testRecipientByTopic.getCreatorId()).isEqualTo(DEFAULT_CREATOR_ID);
    }

    @Test
    public void createRecipientByTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recipientByTopicRepository.findAll().size();

        // Create the RecipientByTopic with an existing ID
        recipientByTopic.setId(UUID.randomUUID());
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(recipientByTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecipientByTopicMockMvc.perform(post("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecipientByTopic in the database
        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkTopicIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = recipientByTopicRepository.findAll().size();
        // set the field null
        recipientByTopic.setTopicId(null);

        // Create the RecipientByTopic, which fails.
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(recipientByTopic);


        restRecipientByTopicMockMvc.perform(post("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isBadRequest());

        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRecipientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = recipientByTopicRepository.findAll().size();
        // set the field null
        recipientByTopic.setRecipientId(null);

        // Create the RecipientByTopic, which fails.
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(recipientByTopic);


        restRecipientByTopicMockMvc.perform(post("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isBadRequest());

        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllRecipientByTopics() throws Exception {
        // Initialize the database
        recipientByTopic.setId(UUID.randomUUID());
        recipientByTopicRepository.save(recipientByTopic);

        // Get all the recipientByTopicList
        restRecipientByTopicMockMvc.perform(get("/api/recipient-by-topics"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recipientByTopic.getId().toString())))
            .andExpect(jsonPath("$.[*].topicId").value(hasItem(DEFAULT_TOPIC_ID.toString())))
            .andExpect(jsonPath("$.[*].recipientId").value(hasItem(DEFAULT_RECIPIENT_ID.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].recipientName").value(hasItem(DEFAULT_RECIPIENT_NAME)))
            .andExpect(jsonPath("$.[*].topicName").value(hasItem(DEFAULT_TOPIC_NAME)))
            .andExpect(jsonPath("$.[*].adderId").value(hasItem(DEFAULT_ADDER_ID.toString())))
            .andExpect(jsonPath("$.[*].creatorId").value(hasItem(DEFAULT_CREATOR_ID.toString())));
    }
    
    @Test
    public void getRecipientByTopic() throws Exception {
        // Initialize the database
        recipientByTopic.setId(UUID.randomUUID());
        recipientByTopicRepository.save(recipientByTopic);

        // Get the recipientByTopic
        restRecipientByTopicMockMvc.perform(get("/api/recipient-by-topics/{id}", recipientByTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recipientByTopic.getId().toString()))
            .andExpect(jsonPath("$.topicId").value(DEFAULT_TOPIC_ID.toString()))
            .andExpect(jsonPath("$.recipientId").value(DEFAULT_RECIPIENT_ID.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.recipientName").value(DEFAULT_RECIPIENT_NAME))
            .andExpect(jsonPath("$.topicName").value(DEFAULT_TOPIC_NAME))
            .andExpect(jsonPath("$.adderId").value(DEFAULT_ADDER_ID.toString()))
            .andExpect(jsonPath("$.creatorId").value(DEFAULT_CREATOR_ID.toString()));
    }
    @Test
    public void getNonExistingRecipientByTopic() throws Exception {
        // Get the recipientByTopic
        restRecipientByTopicMockMvc.perform(get("/api/recipient-by-topics/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRecipientByTopic() throws Exception {
        // Initialize the database
        recipientByTopic.setId(UUID.randomUUID());
        recipientByTopicRepository.save(recipientByTopic);

        int databaseSizeBeforeUpdate = recipientByTopicRepository.findAll().size();

        // Update the recipientByTopic
        RecipientByTopic updatedRecipientByTopic = recipientByTopicRepository.findById(recipientByTopic.getId()).get();
        updatedRecipientByTopic
            .topicId(UPDATED_TOPIC_ID)
            .recipientId(UPDATED_RECIPIENT_ID)
            .role(UPDATED_ROLE)
            .createDate(UPDATED_CREATE_DATE)
            .recipientName(UPDATED_RECIPIENT_NAME)
            .topicName(UPDATED_TOPIC_NAME)
            .adderId(UPDATED_ADDER_ID)
            .creatorId(UPDATED_CREATOR_ID);
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(updatedRecipientByTopic);

        restRecipientByTopicMockMvc.perform(put("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isOk());

        // Validate the RecipientByTopic in the database
        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeUpdate);
        RecipientByTopic testRecipientByTopic = recipientByTopicList.get(recipientByTopicList.size() - 1);
        assertThat(testRecipientByTopic.getTopicId()).isEqualTo(UPDATED_TOPIC_ID);
        assertThat(testRecipientByTopic.getRecipientId()).isEqualTo(UPDATED_RECIPIENT_ID);
        assertThat(testRecipientByTopic.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testRecipientByTopic.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testRecipientByTopic.getRecipientName()).isEqualTo(UPDATED_RECIPIENT_NAME);
        assertThat(testRecipientByTopic.getTopicName()).isEqualTo(UPDATED_TOPIC_NAME);
        assertThat(testRecipientByTopic.getAdderId()).isEqualTo(UPDATED_ADDER_ID);
        assertThat(testRecipientByTopic.getCreatorId()).isEqualTo(UPDATED_CREATOR_ID);
    }

    @Test
    public void updateNonExistingRecipientByTopic() throws Exception {
        int databaseSizeBeforeUpdate = recipientByTopicRepository.findAll().size();

        // Create the RecipientByTopic
        RecipientByTopicDTO recipientByTopicDTO = recipientByTopicMapper.toDto(recipientByTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecipientByTopicMockMvc.perform(put("/api/recipient-by-topics").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recipientByTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecipientByTopic in the database
        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRecipientByTopic() throws Exception {
        // Initialize the database
        recipientByTopic.setId(UUID.randomUUID());
        recipientByTopicRepository.save(recipientByTopic);

        int databaseSizeBeforeDelete = recipientByTopicRepository.findAll().size();

        // Delete the recipientByTopic
        restRecipientByTopicMockMvc.perform(delete("/api/recipient-by-topics/{id}", recipientByTopic.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecipientByTopic> recipientByTopicList = recipientByTopicRepository.findAll();
        assertThat(recipientByTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
