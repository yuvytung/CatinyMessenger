package org.regitiny.catiny.messenger.web.openapi.model;

import lombok.Data;
import org.regitiny.tools.magic.quick.JsonQuickMagic;

@Data
public class Topic extends JsonQuickMagic
{
  private String creator;

  private String role;
}

