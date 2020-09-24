package org.regitiny.catiny.messenger.service.mapper;


import org.regitiny.catiny.messenger.domain.*;
import org.regitiny.catiny.messenger.service.dto.MessageByTopicMessageIdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageByTopicMessageId} and its DTO {@link MessageByTopicMessageIdDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageByTopicMessageIdMapper extends EntityMapper<MessageByTopicMessageIdDTO, MessageByTopicMessageId> {


}
