package org.gojo.controller;

import org.gojo.dto.EventDto;
import org.gojo.entity.EventEntity;
import org.gojo.service.CassandraEventsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventServiceController {

  private final CassandraEventsIntegrationService cassandraEventsIntegrationService;

  @Autowired
  public EventServiceController(CassandraEventsIntegrationService cassandraEventsIntegrationService) {
    this.cassandraEventsIntegrationService = cassandraEventsIntegrationService;
  }

  @PostMapping("/save")
  public ResponseEntity<EventEntity> saveEvent(@RequestBody EventDto eventDto){
    var response = cassandraEventsIntegrationService.saveEvent(eventDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/get")
  public ResponseEntity<EventEntity> getEvent(@RequestParam String eventId){
    var response = cassandraEventsIntegrationService.getEvent(eventId);
    return ResponseEntity.ok(response);
  }

}
