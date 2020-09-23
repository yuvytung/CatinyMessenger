package org.regitiny.catiny.messenger.service.mapper;


import org.regitiny.catiny.messenger.domain.*;
import org.regitiny.catiny.messenger.service.dto.MessageIdByRecipientTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageIdByRecipientTopic} and its DTO {@link MessageIdByRecipientTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageIdByRecipientTopicMapper extends EntityMapper<MessageIdByRecipientTopicDTO, MessageIdByRecipientTopic>
{


}
