package org.regitiny.catiny.messenger.web.openapi;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@Api(value = "messages", description = "the messages API", produces = "application/json", consumes = "application/json")
@RequestMapping("/api/topic")
public interface TopicApi
{

  /**
   * POST /rest/topic : Create topic
   * Create a topic
   *
   * @param topicCreateModel (required)
   * @return create topic succsessful (status code 200)
   */
  @ApiOperation(value = "Create a topic", nickname = "createTopic", notes = "Create a topic and add the user (recipient) to this topic", tags = {"topic"})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "create topic successful"),
    @ApiResponse(code = 405, message = "this topicId already exist")})
  @PostMapping(value = "", consumes = "application/json", produces = "application/json")
  ResponseEntity<String> createTopic(@ApiParam(value = "", required = true) @Valid @NonNull @RequestBody TopicCreateModel topicCreateModel);


  /**
   * POST /rest/topic/add : add recipient to topic
   * add recipient to topic by masterId
   *
   * @param topicAddModel (required)
   * @return add recipient to topic succsessful (status code 200).
   */
  @ApiOperation(value = "add recipient to topic", nickname = "addRecipientToTopic", notes = "add recipient to topic", tags = {"topic"})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "recipient to topic successful"),
    @ApiResponse(code = 400, message = "this topicId not exist")})
  @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
  ResponseEntity<String> addRecipientToTopic(@ApiParam(value = "", required = true) @Valid @NonNull @RequestBody TopicAddModel topicAddModel);


  //  Class models
  @Data
  @NoArgsConstructor
  class TopicAddModel
  {
    @NonNull
    private List<UUID> recipientIds;
    @NonNull
    private UUID topicId;
  }

  @Data
  @NoArgsConstructor
  class TopicCreateModel
  {
    @NonNull
    private List<UUID> recipientIds;
    private String topicName;
  }
}
