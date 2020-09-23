package org.regitiny.catiny.messenger.service.mapper;


import org.regitiny.catiny.messenger.domain.*;
import org.regitiny.catiny.messenger.service.dto.MessageSimpleByRecipientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageSimpleByRecipient} and its DTO {@link MessageSimpleByRecipientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageSimpleByRecipientMapper extends EntityMapper<MessageSimpleByRecipientDTO, MessageSimpleByRecipient>
{


}
