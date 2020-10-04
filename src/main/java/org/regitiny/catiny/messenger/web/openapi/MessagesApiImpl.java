package org.regitiny.catiny.messenger.web.openapi;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.regitiny.catiny.messenger.utils.MasterUtils;
import org.regitiny.catiny.messenger.web.openapi.model.Message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Log4j2
@RestController
public class MessagesApiImpl implements MessagesApi
{

  @Override
  public ResponseEntity<Void> deleteMessage(String messageId)
  {
    return null;
  }

  @Override
  public ResponseEntity<Void> sendMessage(@Valid Message body)
  {
    return null;
  }

  @Override
  public ResponseEntity<Void> updateMessage(String messageId, @Valid Message body)
  {
    return null;
  }

  @Override
  public String pipi()
  {
    MasterDTO masterDTO = MasterUtils.getMaster();

    log.debug(masterDTO);
    log.debug(masterDTO.toJsonString());
    return masterDTO.toJsonString();
  }
}
