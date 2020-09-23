package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.service.MessageIdByRecipientTopicService;
import org.regitiny.catiny.messenger.web.rest.errors.BadRequestAlertException;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link org.regitiny.catiny.messenger.domain.MessageIdByRecipientTopic}.
 */
@RestController
@RequestMapping("/api")
public class MessageIdByRecipientTopicResource {

    private final Logger log = LoggerFactory.getLogger(MessageIdByRecipientTopicResource.class);

    private static final String ENTITY_NAME = "catinyMessengerMessageIdByRecipientTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageIdByRecipientTopicService messageIdByRecipientTopicService;

    public MessageIdByRecipientTopicResource(MessageIdByRecipientTopicService messageIdByRecipientTopicService) {
        this.messageIdByRecipientTopicService = messageIdByRecipientTopicService;
    }

    /**
     * {@code POST  /message-id-by-recipient-topics} : Create a new messageIdByRecipientTopic.
     *
     * @param messageIdByRecipientTopicDTO the messageIdByRecipientTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageIdByRecipientTopicDTO, or with status {@code 400 (Bad Request)} if the messageIdByRecipientTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-id-by-recipient-topics")
    public ResponseEntity<MessageIdByRecipientTopicDTO> createMessageIdByRecipientTopic(@Valid @RequestBody MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO) throws URISyntaxException {
        log.debug("REST request to save MessageIdByRecipientTopic : {}", messageIdByRecipientTopicDTO);
        if (messageIdByRecipientTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageIdByRecipientTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        messageIdByRecipientTopicDTO.setId(UUID.randomUUID());
        MessageIdByRecipientTopicDTO result = messageIdByRecipientTopicService.save(messageIdByRecipientTopicDTO);
        return ResponseEntity.created(new URI("/api/message-id-by-recipient-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /message-id-by-recipient-topics} : Updates an existing messageIdByRecipientTopic.
     *
     * @param messageIdByRecipientTopicDTO the messageIdByRecipientTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageIdByRecipientTopicDTO,
     * or with status {@code 400 (Bad Request)} if the messageIdByRecipientTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageIdByRecipientTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/message-id-by-recipient-topics")
    public ResponseEntity<MessageIdByRecipientTopicDTO> updateMessageIdByRecipientTopic(@Valid @RequestBody MessageIdByRecipientTopicDTO messageIdByRecipientTopicDTO) throws URISyntaxException {
        log.debug("REST request to update MessageIdByRecipientTopic : {}", messageIdByRecipientTopicDTO);
        if (messageIdByRecipientTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessageIdByRecipientTopicDTO result = messageIdByRecipientTopicService.save(messageIdByRecipientTopicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageIdByRecipientTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /message-id-by-recipient-topics} : get all the messageIdByRecipientTopics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageIdByRecipientTopics in body.
     */
    @GetMapping("/message-id-by-recipient-topics")
    public List<MessageIdByRecipientTopicDTO> getAllMessageIdByRecipientTopics() {
        log.debug("REST request to get all MessageIdByRecipientTopics");
        return messageIdByRecipientTopicService.findAll();
    }

    /**
     * {@code GET  /message-id-by-recipient-topics/:id} : get the "id" messageIdByRecipientTopic.
     *
     * @param id the id of the messageIdByRecipientTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageIdByRecipientTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/message-id-by-recipient-topics/{id}")
    public ResponseEntity<MessageIdByRecipientTopicDTO> getMessageIdByRecipientTopic(@PathVariable UUID id) {
        log.debug("REST request to get MessageIdByRecipientTopic : {}", id);
        Optional<MessageIdByRecipientTopicDTO> messageIdByRecipientTopicDTO = messageIdByRecipientTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageIdByRecipientTopicDTO);
    }

    /**
     * {@code DELETE  /message-id-by-recipient-topics/:id} : delete the "id" messageIdByRecipientTopic.
     *
     * @param id the id of the messageIdByRecipientTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/message-id-by-recipient-topics/{id}")
    public ResponseEntity<Void> deleteMessageIdByRecipientTopic(@PathVariable UUID id) {
        log.debug("REST request to delete MessageIdByRecipientTopic : {}", id);
        messageIdByRecipientTopicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
