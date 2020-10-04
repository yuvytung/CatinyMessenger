package org.regitiny.catiny.messenger.web.openapi;

import io.swagger.annotations.*;
import org.regitiny.catiny.messenger.web.openapi.model.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Validated
@Api(value = "messages", description = "the messages API")
@RequestMapping("/rest/topic")
public interface TopicApi
{

  /**
   * POST /messages/topic : Create topic
   * Create a topic
   *
   * @param body (required)
   * @return create topic succsessful (status code 200)
   */
  @ApiOperation(value = "Create topic", nickname = "createTopic", notes = "Create a topic", tags = {"messages",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "create topic succsessful")})
  @PostMapping(value = "", consumes = "application/json", produces = "application/json")
  ResponseEntity<Void> createTopic(@ApiParam(value = "", required = true) @Valid @RequestBody Topic body);

}
