package org.regitiny.catiny.messenger.utils;

import lombok.extern.log4j.Log4j2;

import org.regitiny.catiny.messenger.security.SecurityUtils;
import org.regitiny.catiny.messenger.service.dto.kafka.MasterDTO;

import org.regitiny.catiny.messenger.utils.impl.MasterLocalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class MasterUtils
{

  private static MasterLocalServices masterLocalServices;

  @Autowired
  private void setMasterLocalServices(MasterLocalServices masterLocalServices)
  {
    MasterUtils.masterLocalServices = masterLocalServices;
  }

  public static MasterDTO getMasterByUsername_G_T(String userName, Long groupId, Long topicId)
  {
    return masterLocalServices.getMasterByUsername_G_C(userName, groupId, topicId);
  }

  public static MasterDTO getMasterDefault()
  {
    String username = SecurityUtils.getCurrentUserLogin().orElse(null);
    return masterLocalServices.getMasterByUsername_G_C(username, 0L, 0L);
  }

  public static MasterDTO getMasterByMasterId(UUID masterId)
  {
    return masterLocalServices.getMasterByMasterId(masterId);
  }

  public static MasterDTO updateMasterDTO(MasterDTO masterDTO)
  {
    return masterLocalServices.updateMasterDTO(masterDTO);
  }

}
