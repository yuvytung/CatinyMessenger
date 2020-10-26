package org.regitiny.catiny.messenger.business.impl;

import lombok.extern.log4j.Log4j2;

import org.json.JSONObject;

import org.regitiny.catiny.messenger.business.TopicBusiness;
import org.regitiny.catiny.messenger.domain.RecipientByTopic;
import org.regitiny.catiny.messenger.exception.ExistException;
import org.regitiny.catiny.messenger.exception.NotExistException;
import org.regitiny.catiny.messenger.service.RecipientByTopicService;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;
import org.regitiny.catiny.messenger.service.mapper.RecipientByTopicMapper;
import org.regitiny.catiny.messenger.utils.MasterUtils;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class TopicBusinessImpl implements TopicBusiness
{
  private final RecipientByTopicService recipientByTopicService;
  private final RecipientByTopicMapper recipientByTopicMapper;


  public TopicBusinessImpl(RecipientByTopicService recipientByTopicService, RecipientByTopicMapper recipientByTopicMapper)
  {
    this.recipientByTopicService = recipientByTopicService;
    this.recipientByTopicMapper = recipientByTopicMapper;
  }

  private JSONObject createTopicPair(UUID toMasterId, String topicName)
  {
    MasterDTO fromMasterDTO = MasterUtils.getMasterDefault();
    MasterDTO toMasterDTO = MasterUtils.getMasterByMasterId(toMasterId);

    if (Objects.isNull(fromMasterDTO) || Objects.isNull(toMasterDTO))
      throw new NullPointerException("fromMasterDTO and toMasterDTO must required");

    UUID fromMasterId = fromMasterDTO.getMasterId();
    UUID newTopicId;
    byte[] hashTopicId;
    String sumMasterId;
    if (fromMasterId.compareTo(toMasterId) < 0)
      sumMasterId = fromMasterId.toString() + toMasterId.toString();
    else
      sumMasterId = toMasterId.toString() + fromMasterId.toString();

    try
    {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      hashTopicId = messageDigest.digest(sumMasterId.getBytes(StandardCharsets.UTF_8));
      newTopicId = UUID.nameUUIDFromBytes(hashTopicId);
    }
    catch (NoSuchAlgorithmException e)
    {
      log.warn("err name Algorithm", e);
      return null;
    }

    if (recipientByTopicService.findByTopic(newTopicId).size() > 0)
      throw new ExistException("This topic already exists");

    Instant now = Instant.now();

    // set shared properties
    RecipientByTopic recipientByTopic = new RecipientByTopic();
    recipientByTopic.setTopicId(newTopicId);
    recipientByTopic.setTopicName(topicName);
    recipientByTopic.setCreateDate(now);
    recipientByTopic.setRole("0");
    recipientByTopic.setAdderId(fromMasterId);
    recipientByTopic.setCreatorId(fromMasterId);

    // set from properties and save
    recipientByTopic.setRecipientId(fromMasterDTO.getMasterId());
    recipientByTopic.setRecipientName(fromMasterDTO.getMasterUserName());
    log.debug("recipientByTopic = {}", recipientByTopic);
    recipientByTopicService.save(recipientByTopicMapper.toDto(recipientByTopic));

    //set to properties and save
    recipientByTopic.setRecipientId(toMasterDTO.getMasterId());
    recipientByTopic.setRecipientName(toMasterDTO.getMasterUserName());
    log.debug("recipientByTopic = {}", recipientByTopic);
    recipientByTopicService.save(recipientByTopicMapper.toDto(recipientByTopic));

    JSONObject jsonResult = new JSONObject();
    jsonResult.put("topicId", newTopicId.toString());
    jsonResult.put("topicName", topicName);
    jsonResult.put("status", "created");

    return jsonResult;
  }

  private JSONObject createTopicGroup(List<UUID> toMasterIds, String topicName)
  {
    MasterDTO fromMasterDTO = MasterUtils.getMasterDefault();
    List<MasterDTO> toMasterDTOs = new ArrayList<>();
    toMasterDTOs.add(fromMasterDTO);
    for (UUID masterIds : toMasterIds)
    {
      MasterDTO masterDTO = MasterUtils.getMasterByMasterId(masterIds);
      if (Objects.nonNull(masterDTO))
        toMasterDTOs.add(masterDTO);
    }

    if (Objects.isNull(fromMasterDTO) || toMasterDTOs.size() < 2)
      throw new NullPointerException("fromMasterDTO and toMasterDTO must required");

    UUID newTopicId = UUID.randomUUID();
    Instant now = Instant.now();

    for (MasterDTO master : toMasterDTOs)
    {
      // set shared properties
      RecipientByTopic recipientByTopic = new RecipientByTopic();
      recipientByTopic.setTopicId(newTopicId);
      recipientByTopic.setTopicName(topicName);
      recipientByTopic.setCreateDate(now);
      recipientByTopic.setRole("10");
      recipientByTopic.setAdderId(fromMasterDTO.getMasterId());
      recipientByTopic.setCreatorId(fromMasterDTO.getMasterId());

      //set to properties and save
      recipientByTopic.setRecipientId(master.getMasterId());
      recipientByTopic.setRecipientName(master.getMasterUserName());
      log.debug("recipientByTopic = {}", recipientByTopic);
      recipientByTopicService.save(recipientByTopicMapper.toDto(recipientByTopic));
    }

    RecipientByTopicDTO recipientByTopic = recipientByTopicService.fetchOne(newTopicId, fromMasterDTO.getMasterId());
    // set from properties and save
    recipientByTopic.setRole("0");
    recipientByTopic.setRecipientId(fromMasterDTO.getMasterId());
    recipientByTopic.setRecipientName(fromMasterDTO.getMasterUserName());
    log.debug("recipientByTopic = {}", recipientByTopic);
    recipientByTopicService.save(recipientByTopic);

    JSONObject jsonResult = new JSONObject();
    jsonResult.put("topicId", newTopicId.toString());
    jsonResult.put("topicName", topicName);
    jsonResult.put("status", "created");

    return jsonResult;
  }

  @Override
  public JSONObject createTopic(List<UUID> masterIds, String topicName)
  {
    if (masterIds.size() == 1)
      return createTopicPair(masterIds.get(0), topicName);
    else
      return createTopicGroup(masterIds, topicName);
  }

  @Override
  public JSONObject addRecipientToTopic(List<UUID> recipientIds, UUID topicId)
  {
    MasterDTO masterDTO = MasterUtils.getMasterDefault();
    RecipientByTopicDTO recipientByTopic;
    log.debug("recipientIds = {} \n topicId = {}", recipientIds, topicId);

    Instant now = Instant.now();

    recipientByTopic = recipientByTopicService.fetchOne(topicId, masterDTO.getMasterId());
    log.debug(recipientByTopic);
    if (Objects.isNull(recipientByTopic))
      throw new NotExistException("the topic not exist or this user not in the topic");
    for (UUID recipientId : recipientIds)
    {
      if (Objects.isNull(MasterUtils.getMasterByMasterId(recipientId)))
      {
        log.warn("user not exist . detail  : masterId= {}" , recipientId);
        continue;
      }
      RecipientByTopicDTO exist = recipientByTopicService.fetchOne(topicId, recipientId);
      log.debug("recipientId = {} \n exist = {}", recipientId, exist);
      if (Objects.nonNull(exist))
        throw new ExistException("This user already exist in the topic");
      if (Objects.nonNull(MasterUtils.getMasterByMasterId(recipientId)))
      {
        recipientByTopic.setRecipientId(recipientId);

        recipientByTopic.setAdderId(masterDTO.getMasterId());
        recipientByTopic.setCreateDate(now);
        recipientByTopic.setRole("10");
        recipientByTopic.setRecipientName(masterDTO.getMasterUserName());
        log.debug("recipientByTopic = {}", recipientByTopic);
        recipientByTopicService.save(recipientByTopic);
      }
    }
    JSONObject jsonResult = new JSONObject();
    jsonResult.put("status", "added");
    return jsonResult;
  }
}
