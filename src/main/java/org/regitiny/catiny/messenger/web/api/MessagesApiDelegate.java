package org.regitiny.catiny.messenger.web.api;


import org.regitiny.catiny.messenger.web.api.model.Message;
import org.regitiny.catiny.messenger.web.api.model.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

/**
 * A delegate to be called by the {@link MessagesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-19T21:01:24.515+07:00[Asia/Bangkok]")

public interface MessagesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /messages/topic : Create topic
     * Create a topic
     *
     * @param body  (required)
     * @return create topic succsessful (status code 200)
     * @see MessagesApi#createTopic
     */
    default ResponseEntity<Void> createTopic(Topic body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /messages/{messageId} : Delete message
     * Delete a message by messageId
     *
     * @param messageId  (required)
     * @return delete message successful (status code 200)
     * @see MessagesApi#deleteMessage
     */
    default ResponseEntity<Void> deleteMessage(String messageId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /messages/send : send message
     * Send a message to a topic
     *
     * @param body Information and the content of the message (required)
     * @return send message successful (status code 200)
     *         or Validation exception (status code 405)
     * @see MessagesApi#sendMessage
     */
    default ResponseEntity<Void> sendMessage(Message body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /messages/{messageId} : Edit message
     * Edit a message by messageID
     *
     * @param messageId  (required)
     * @param body Content to edit (optional)
     * @return edit message successful (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Message not found (status code 404)
     *         or Validation exception (status code 405)
     * @see MessagesApi#updateMessage
     */
    default ResponseEntity<Void> updateMessage(String messageId,
        Message body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
