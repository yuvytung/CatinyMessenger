package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.service.MessageByTopicMessageIdService;
import org.regitiny.catiny.messenger.web.rest.errors.BadRequestAlertException;
import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;

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
 * REST controller for managing {@link org.regitiny.catiny.messenger.domain.MessageByTopicMessageId}.
 */
@RestController
@RequestMapping("/api")
public class MessageByTopicMessageIdResource {

    private final Logger log = LoggerFactory.getLogger(MessageByTopicMessageIdResource.class);

    private static final String ENTITY_NAME = "catinyMessengerMessageByTopicMessageId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageByTopicMessageIdService messageByTopicMessageIdService;

    public MessageByTopicMessageIdResource(MessageByTopicMessageIdService messageByTopicMessageIdService) {
        this.messageByTopicMessageIdService = messageByTopicMessageIdService;
    }

    /**
     * {@code POST  /message-by-topic-message-ids} : Create a new messageByTopicMessageId.
     *
     * @param messageByTopicMessageIdDTO the messageByTopicMessageIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageByTopicMessageIdDTO, or with status {@code 400 (Bad Request)} if the messageByTopicMessageId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-by-topic-message-ids")
    public ResponseEntity<MessageByTopicMessageIdDTO> createMessageByTopicMessageId(@Valid @RequestBody MessageByTopicMessageIdDTO messageByTopicMessageIdDTO) throws URISyntaxException {
        log.debug("REST request to save MessageByTopicMessageId : {}", messageByTopicMessageIdDTO);
        if (messageByTopicMessageIdDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageByTopicMessageId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        messageByTopicMessageIdDTO.setId(UUID.randomUUID());
        MessageByTopicMessageIdDTO result = messageByTopicMessageIdService.save(messageByTopicMessageIdDTO);
        return ResponseEntity.created(new URI("/api/message-by-topic-message-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /message-by-topic-message-ids} : Updates an existing messageByTopicMessageId.
     *
     * @param messageByTopicMessageIdDTO the messageByTopicMessageIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageByTopicMessageIdDTO,
     * or with status {@code 400 (Bad Request)} if the messageByTopicMessageIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageByTopicMessageIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/message-by-topic-message-ids")
    public ResponseEntity<MessageByTopicMessageIdDTO> updateMessageByTopicMessageId(@Valid @RequestBody MessageByTopicMessageIdDTO messageByTopicMessageIdDTO) throws URISyntaxException {
        log.debug("REST request to update MessageByTopicMessageId : {}", messageByTopicMessageIdDTO);
        if (messageByTopicMessageIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessageByTopicMessageIdDTO result = messageByTopicMessageIdService.save(messageByTopicMessageIdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageByTopicMessageIdDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /message-by-topic-message-ids} : get all the messageByTopicMessageIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageByTopicMessageIds in body.
     */
    @GetMapping("/message-by-topic-message-ids")
    public List<MessageByTopicMessageIdDTO> getAllMessageByTopicMessageIds() {
        log.debug("REST request to get all MessageByTopicMessageIds");
        return messageByTopicMessageIdService.findAll();
    }

    /**
     * {@code GET  /message-by-topic-message-ids/:id} : get the "id" messageByTopicMessageId.
     *
     * @param id the id of the messageByTopicMessageIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageByTopicMessageIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/message-by-topic-message-ids/{id}")
    public ResponseEntity<MessageByTopicMessageIdDTO> getMessageByTopicMessageId(@PathVariable UUID id) {
        log.debug("REST request to get MessageByTopicMessageId : {}", id);
        Optional<MessageByTopicMessageIdDTO> messageByTopicMessageIdDTO = messageByTopicMessageIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageByTopicMessageIdDTO);
    }

    /**
     * {@code DELETE  /message-by-topic-message-ids/:id} : delete the "id" messageByTopicMessageId.
     *
     * @param id the id of the messageByTopicMessageIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/message-by-topic-message-ids/{id}")
    public ResponseEntity<Void> deleteMessageByTopicMessageId(@PathVariable UUID id) {
        log.debug("REST request to delete MessageByTopicMessageId : {}", id);
        messageByTopicMessageIdService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
