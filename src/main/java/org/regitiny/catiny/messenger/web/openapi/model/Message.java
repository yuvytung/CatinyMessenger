package org.regitiny.catiny.messenger.web.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Message
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-19T21:01:24.515+07:00[Asia/Bangkok]")

public class Message   {
  @JsonProperty("sender")
  private String sender;

  @JsonProperty("sendDate")
  private String sendDate;

  @JsonProperty("topicId")
  private String topicId;

  public Message sender(String sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Get sender
   * @return sender
  */
  @ApiModelProperty(value = "")


  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Message sendDate(String sendDate) {
    this.sendDate = sendDate;
    return this;
  }

  /**
   * Get sendDate
   * @return sendDate
  */
  @ApiModelProperty(value = "")


  public String getSendDate() {
    return sendDate;
  }

  public void setSendDate(String sendDate) {
    this.sendDate = sendDate;
  }

  public Message topicId(String topicId) {
    this.topicId = topicId;
    return this;
  }

  /**
   * Get topicId
   * @return topicId
  */
  @ApiModelProperty(value = "")


  public String getTopicId() {
    return topicId;
  }

  public void setTopicId(String topicId) {
    this.topicId = topicId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.sender, message.sender) &&
        Objects.equals(this.sendDate, message.sendDate) &&
        Objects.equals(this.topicId, message.topicId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, sendDate, topicId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");

    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    sendDate: ").append(toIndentedString(sendDate)).append("\n");
    sb.append("    topicId: ").append(toIndentedString(topicId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

