package org.regitiny.catiny.messenger.web.openapi.model;

import lombok.Data;
import org.regitiny.tools.magic.quick.JsonQuickMagic;

@Data
public class Message extends JsonQuickMagic
{
  private String sender;

  private String sendDate;

  private String topicId;
}

