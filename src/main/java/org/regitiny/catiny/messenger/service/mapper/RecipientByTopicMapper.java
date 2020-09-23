package org.regitiny.catiny.messenger.service.mapper;


import org.regitiny.catiny.messenger.domain.*;
import org.regitiny.catiny.messenger.service.dto.RecipientByTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecipientByTopic} and its DTO {@link RecipientByTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecipientByTopicMapper extends EntityMapper<RecipientByTopicDTO, RecipientByTopic> {


}
