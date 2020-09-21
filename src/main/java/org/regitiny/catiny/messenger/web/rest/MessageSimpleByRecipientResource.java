package org.regitiny.catiny.messenger.web.rest;

import org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient;
import org.regitiny.catiny.messenger.service.MessageSimpleByRecipientService;
import org.regitiny.catiny.messenger.web.rest.api.MessageSimpleByRecipientApi;
import org.regitiny.catiny.messenger.web.rest.errors.BadRequestAlertException;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link org.regitiny.catiny.messenger.domain.MessageSimpleByRecipient}.
 */
@RestController
@RequestMapping("/api/message-simple")
public class MessageSimpleByRecipientResource implements MessageSimpleByRecipientApi
{

  private final Logger log = LoggerFactory.getLogger(MessageSimpleByRecipientResource.class);

  private static final String ENTITY_NAME = "catinyMessengerMessageSimpleByRecipient";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final MessageSimpleByRecipientService messageSimpleByRecipientService;

  public MessageSimpleByRecipientResource(MessageSimpleByRecipientService messageSimpleByRecipientService)
  {
    this.messageSimpleByRecipientService = messageSimpleByRecipientService;
  }

//  /**
//   * {@code POST  /message-simple-by-recipients} : Create a new messageSimpleByRecipient.
//   *
//   * @param messageSimpleByRecipientDTO the messageSimpleByRecipientDTO to create.
//   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageSimpleByRecipientDTO, or with status {@code 400 (Bad Request)} if the messageSimpleByRecipient has already an ID.
//   * @throws URISyntaxException if the Location URI syntax is incorrect.
//   */
//    @PostMapping("/message-simple-by-recipients")
//    public ResponseEntity<MessageSimpleByRecipientDTO> createMessageSimpleByRecipient(@Valid @RequestBody MessageSimpleByRecipientDTO messageSimpleByRecipientDTO) throws URISyntaxException {
//        log.debug("REST request to save MessageSimpleByRecipient : {}", messageSimpleByRecipientDTO);
//        if (messageSimpleByRecipientDTO.getId() != null) {
//            throw new BadRequestAlertException("A new messageSimpleByRecipient cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        messageSimpleByRecipientDTO.setId(UUID.randomUUID());
//        MessageSimpleByRecipientDTO result = messageSimpleByRecipientService.save(messageSimpleByRecipientDTO);
//        return ResponseEntity.created(new URI("/api/message-simple-by-recipients/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//  /**
//   * {@code PUT  /message-simple-by-recipients} : Updates an existing messageSimpleByRecipient.
//   *
//   * @param messageSimpleByRecipientDTO the messageSimpleByRecipientDTO to update.
//   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageSimpleByRecipientDTO,
//   * or with status {@code 400 (Bad Request)} if the messageSimpleByRecipientDTO is not valid,
//   * or with status {@code 500 (Internal Server Error)} if the messageSimpleByRecipientDTO couldn't be updated.
//   * @throws URISyntaxException if the Location URI syntax is incorrect.
//   */
//    @PutMapping("/message-simple-by-recipients")
//    public ResponseEntity<MessageSimpleByRecipientDTO> updateMessageSimpleByRecipient(@Valid @RequestBody MessageSimpleByRecipientDTO messageSimpleByRecipientDTO) throws URISyntaxException {
//        log.debug("REST request to update MessageSimpleByRecipient : {}", messageSimpleByRecipientDTO);
//        if (messageSimpleByRecipientDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        MessageSimpleByRecipientDTO result = messageSimpleByRecipientService.save(messageSimpleByRecipientDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageSimpleByRecipientDTO.getId().toString()))
//            .body(result);
//    }

//  /**
//   * {@code GET  /message-simple-by-recipients/:id} : get the "id" messageSimpleByRecipient.
//   *
//   * @param id the id of the messageSimpleByRecipientDTO to retrieve.
//   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageSimpleByRecipientDTO, or with status {@code 404 (Not Found)}.
//   */
//    @GetMapping("/message-simple-by-recipients/{id}")
//    public ResponseEntity<MessageSimpleByRecipientDTO> getMessageSimpleByRecipient(@PathVariable UUID id) {
//        log.debug("REST request to get MessageSimpleByRecipient : {}", id);
//        Optional<MessageSimpleByRecipientDTO> messageSimpleByRecipientDTO = messageSimpleByRecipientService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(messageSimpleByRecipientDTO);
//    }

  /**
   * {@code DELETE  /pk} : delete the "id" messageSimpleByRecipient.
   *
   * @param recipientId and {@param topicId} is the primaryKey of the messageSimpleByRecipientDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/pk")
  public ResponseEntity<Void> deleteMessageSimpleByPK(@RequestParam UUID recipientId, @RequestParam UUID topicId)
  {
    log.debug("REST request to delete MessageSimpleByRecipient : {}", recipientId);
    messageSimpleByRecipientService.delete(recipientId, topicId);
    return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "recipientId" + recipientId.toString() + ", topicId: " + topicId.toString())).build();
  }

  /**
   * {@code GET  /all} : get all the messageSimpleByRecipients.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageSimpleByRecipients in body.
   */
  @GetMapping("/all")
  public List<MessageSimpleByRecipientDTO> getAllMessageSimple()
  {
    log.debug("REST request to get all MessageSimpleByRecipients");
    return messageSimpleByRecipientService.findAll();
  }

  @GetMapping("/pk")
  public ResponseEntity<MessageSimpleByRecipientDTO> getMessageSimpleByPk(@RequestParam UUID recipientId, @RequestParam UUID topicId)
  {
    log.debug("REST request to get MessageSimpleByRecipient , recipientId = {} ,  topicId = {} ", recipientId, topicId);
    MessageSimpleByRecipientDTO messageSimpleByRecipientDTO = messageSimpleByRecipientService.fetchOne(recipientId, topicId);
    return ResponseEntity.ok(messageSimpleByRecipientDTO);
  }

  @GetMapping("/last-each-topic")
  public ResponseEntity<List<MessageSimpleByRecipientDTO>> getMessageSimpleByPk(@RequestParam UUID recipientId)
  {
    log.debug("REST request to get MessageSimpleByRecipient , recipientId = {} ", recipientId);
    List<MessageSimpleByRecipientDTO> messageSimpleByRecipientDTO = messageSimpleByRecipientService.findByRecipientId(recipientId);
    return ResponseEntity.ok(messageSimpleByRecipientDTO);
  }


}
