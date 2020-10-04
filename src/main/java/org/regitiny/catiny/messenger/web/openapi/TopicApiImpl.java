package org.regitiny.catiny.messenger.web.openapi;

import org.regitiny.catiny.messenger.web.openapi.model.Topic;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public class TopicApiImpl implements TopicApi
{
  @Override
  public ResponseEntity<Void> createTopic(@Valid Topic body)
  {

    return null;
  }
}
