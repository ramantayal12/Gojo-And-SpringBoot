package org.gojo.service.implementation;

import org.gojo.dto.EventDto;
import org.gojo.entity.EventEntity;
import org.gojo.exception.EventNotFoundException;
import org.gojo.repository.EventsCassandraRepository;
import org.gojo.service.CassandraEventsIntegrationService;
import org.gojo.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CassandraEventsIntegrationServiceImpl implements CassandraEventsIntegrationService {

  private final EventsCassandraRepository repository;
  private final IdGenerator idGenerator;

  @Autowired
  public CassandraEventsIntegrationServiceImpl(EventsCassandraRepository repository,
      IdGenerator idGenerator) {
    this.repository = repository;
    this.idGenerator = idGenerator;
  }

  @Override
  public EventEntity saveEvent(EventDto eventDto) {

    EventEntity event = EventEntity.builder()
        .eventId(idGenerator.generateId("E"))
        .eventName(eventDto.getEventName())
        .eventData(eventDto.getEventData())
        .triggerTime(System.currentTimeMillis())
        .build();

    return repository.save(event);
  }

  @Override
  public EventEntity getEvent(String eventId) {
    return repository.findById(eventId)
        .orElseThrow(() -> new EventNotFoundException("Event doesn't exists in dB"));
  }


}
