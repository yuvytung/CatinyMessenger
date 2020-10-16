package org.regitiny.catiny.messenger.utils.impl;

import lombok.extern.log4j.Log4j2;
import org.regitiny.catiny.messenger.constants.CacheName;
import org.regitiny.catiny.messenger.exception.NotExistException;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.UUID;

@Log4j2
@Component
public class MasterLocalServices
{
  private final RestTemplate restTemplate;

  private static final String CATINY_UAA_MASTER_URL = "http://catinyuaa/api/deep/master";

  public MasterLocalServices(RestTemplate loadBalancedRestTemplate)
  {
    restTemplate = loadBalancedRestTemplate;
  }

  @Cacheable(cacheNames = CacheName.CATINY_UAA_MASTER, key = "{#masterId}")
  public MasterDTO getMasterByMasterId(UUID masterId)
  {
    log.debug("masterId = {}", masterId);

    UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(CATINY_UAA_MASTER_URL);
    String url = uriBuilder.pathSegment("{masterId}").build(masterId).toString();

    log.debug("uriBuilder = {}", url);
    MasterDTO response = restTemplate.getForObject(url, MasterDTO.class);

    log.debug("response masterDTO = {}", response);
    if (Objects.nonNull(response))
      return response;
    throw new NotExistException();
  }

  @Cacheable(cacheNames = CacheName.CATINY_UAA_MASTER, key = "{#userName,#groupId,#companyId}")
  public MasterDTO getMasterByUsername_G_C(String userName, Long groupId, Long companyId)
  {
    log.debug("username = {} , groupId = {} , companyId = {}", userName, groupId, companyId);
    if (Objects.isNull(userName))
      return null;

    UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(CATINY_UAA_MASTER_URL);
    uriBuilder.queryParam("userName", userName);
    uriBuilder.queryParam("groupId", groupId);
    uriBuilder.queryParam("companyId", companyId);

    log.debug("uriBuilder = {}", uriBuilder.build());

    MasterDTO response = restTemplate.getForObject(uriBuilder.build(), MasterDTO.class);

    log.debug("response masterDTO = {}", response);
    if (Objects.nonNull(response))
      return response;
    throw new NotExistException();
  }

  @Caching(put =
    {
      @CachePut(cacheNames = CacheName.CATINY_UAA_MASTER, key = "{#masterDTO.masterId}"),
      @CachePut(cacheNames = CacheName.CATINY_UAA_MASTER, key = "{#masterDTO.userName,#masterDTO.groupId,#masterDTO.companyId}")
    })
  public MasterDTO updateMasterDTO(MasterDTO masterDTO)
  {
    log.debug(masterDTO);
    return masterDTO;
  }
}
