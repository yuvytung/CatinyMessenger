package org.regitiny.catiny.messenger.service.mapper;


import org.regitiny.catiny.messenger.domain.*;
import org.regitiny.catiny.messenger.service.dto.MasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Master} and its DTO {@link MasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MasterMapper extends EntityMapper<MasterDTO, Master> {



    default Master fromId(Long id) {
        if (id == null) {
            return null;
        }
        Master master = new Master();
        master.setId(id);
        return master;
    }
}
