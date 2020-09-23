package org.regitiny.catiny.messenger.web.rest;

import org.json.JSONObject;
import org.regitiny.catiny.messenger.domain.RecipientByTopic;
import org.regitiny.catiny.messenger.service.RecipientByTopicService;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;
import org.regitiny.catiny.messenger.service.mapper.RecipientByTopicMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link org.regitiny.catiny.messenger.domain.RecipientByTopic}.
 */
@RestController
@RequestMapping("/api/entity/recipient")
public class RecipientByTopicResource
{

  private final Logger log = LoggerFactory.getLogger(RecipientByTopicResource.class);

  private static final String ENTITY_NAME = "catinyMessengerRecipientByTopic";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final RecipientByTopicService recipientByTopicService;

  private final RecipientByTopicMapper recipientByTopicMapper;

  public RecipientByTopicResource(RecipientByTopicService recipientByTopicService, RecipientByTopicMapper recipientByTopicMapper)
  {
    this.recipientByTopicService = recipientByTopicService;
    this.recipientByTopicMapper = recipientByTopicMapper;
  }


  /**
   * {@code POST  /check} : recipientByTopic check CRUD .
   *
   * @param requestParam the recipientByTopicDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recipientByTopicDTO, or with status {@code 400 (Bad Request)} if the recipientByTopic has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/services/check")
  public ResponseEntity<String> createRecipientByTopic(@RequestParam String requestParam) throws URISyntaxException
  {
    log.debug("REST requestParem : {}", requestParam);
    JSONObject jsonObject = new JSONObject();
    RecipientByTopic recipient = recipientByTopicService.create();
    jsonObject.put("createResult", recipientByTopicMapper.toDto(recipient).toJsonObject());
    recipient.setRecipientName("new name Update");
    RecipientByTopicDTO recipientByTopicDTO = recipientByTopicService.save(recipientByTopicMapper.toDto(recipient));
    jsonObject.put("UpdateByDTO", recipientByTopicDTO.toJsonObject());
    List<RecipientByTopicDTO> recipientByTopics = recipientByTopicService.findByTopic(recipientByTopicDTO.getTopicId());
    jsonObject.put("getByTopicId", recipientByTopics);
    jsonObject.put("deleteStatus", recipientByTopicService.delete(recipientByTopicDTO.getTopicId(),recipientByTopicDTO.getRecipientId()));

    RecipientByTopicDTO recipientByTopicDTOxxx = recipientByTopicDTO.fromJson(recipientByTopicDTO.toJsonString());
    jsonObject.put("test Cái này ok thì ngon" , recipientByTopicDTOxxx.toJsonString());
    return ResponseEntity.ok(jsonObject.toString());
  }

//
//    /**
//     * {@code POST  /recipient-by-topics} : Create a new recipientByTopic.
//     *
//     * @param recipientByTopicDTO the recipientByTopicDTO to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recipientByTopicDTO, or with status {@code 400 (Bad Request)} if the recipientByTopic has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/recipient-by-topics")
//    public ResponseEntity<RecipientByTopicDTO> createRecipientByTopic(@Valid @RequestBody RecipientByTopicDTO recipientByTopicDTO) throws URISyntaxException {
//        log.debug("REST request to save RecipientByTopic : {}", recipientByTopicDTO);
//        if (recipientByTopicDTO.getId() != null) {
//            throw new BadRequestAlertException("A new recipientByTopic cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        recipientByTopicDTO.setId(UUID.randomUUID());
//        RecipientByTopicDTO result = recipientByTopicService.save(recipientByTopicDTO);
//        return ResponseEntity.created(new URI("/api/recipient-by-topics/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code PUT  /recipient-by-topics} : Updates an existing recipientByTopic.
//     *
//     * @param recipientByTopicDTO the recipientByTopicDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recipientByTopicDTO,
//     * or with status {@code 400 (Bad Request)} if the recipientByTopicDTO is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the recipientByTopicDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/recipient-by-topics")
//    public ResponseEntity<RecipientByTopicDTO> updateRecipientByTopic(@Valid @RequestBody RecipientByTopicDTO recipientByTopicDTO) throws URISyntaxException {
//        log.debug("REST request to update RecipientByTopic : {}", recipientByTopicDTO);
//        if (recipientByTopicDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        RecipientByTopicDTO result = recipientByTopicService.save(recipientByTopicDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recipientByTopicDTO.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code GET  /recipient-by-topics} : get all the recipientByTopics.
//     *
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recipientByTopics in body.
//     */
//    @GetMapping("/recipient-by-topics")
//    public List<RecipientByTopicDTO> getAllRecipientByTopics() {
//        log.debug("REST request to get all RecipientByTopics");
//        return recipientByTopicService.findAll();
//    }
//
//    /**
//     * {@code GET  /recipient-by-topics/:id} : get the "id" recipientByTopic.
//     *
//     * @param id the id of the recipientByTopicDTO to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recipientByTopicDTO, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/recipient-by-topics/{id}")
//    public ResponseEntity<RecipientByTopicDTO> getRecipientByTopic(@PathVariable UUID id) {
//        log.debug("REST request to get RecipientByTopic : {}", id);
//        Optional<RecipientByTopicDTO> recipientByTopicDTO = recipientByTopicService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(recipientByTopicDTO);
//    }
//
//    /**
//     * {@code DELETE  /recipient-by-topics/:id} : delete the "id" recipientByTopic.
//     *
//     * @param id the id of the recipientByTopicDTO to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/recipient-by-topics/{id}")
//    public ResponseEntity<Void> deleteRecipientByTopic(@PathVariable UUID id) {
//        log.debug("REST request to delete RecipientByTopic : {}", id);
//        recipientByTopicService.delete(id);
//        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
}
