package org.gojo.service;

import org.gojo.dto.EventDto;
import org.gojo.entity.EventEntity;

public interface CassandraEventsIntegrationService {

  EventEntity saveEvent(EventDto eventDto);

  EventEntity getEvent(String eventId);
}
