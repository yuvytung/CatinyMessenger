package org.regitiny.catiny.messenger.web.openapi.impl;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.regitiny.catiny.messenger.utils.MasterUtils;
import org.regitiny.catiny.messenger.web.openapi.MessagesApi;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    MasterDTO masterDTO = MasterUtils.getMasterDefault();

    log.debug(masterDTO);
    log.debug(masterDTO.toJsonString());
    return masterDTO.toJsonString();
  }

  @GetMapping("/deep/{id}")
  @Cacheable(cacheNames = "pika" , key ="{#id}")
  public String cachePutx(@PathVariable("id") String id)
  {
    System.out.println( "chạy ngon đét");
    return "xx"+id;
  }

  @GetMapping("/deep/{id}/{di}")
  @CachePut(key = "{#id}" , cacheNames = "pika")
  public String cachePut(@PathVariable("id") String id,@PathVariable("di") String di)
  {
    System.out.println( "chạy ngon đét");
    return "xx"+id+di;
  }

}
