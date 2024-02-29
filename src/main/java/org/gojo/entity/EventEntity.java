package org.gojo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

@Data// contains both Getter/Setter
@Builder
@Table("events")
public class EventEntity {

  @Id
  private String eventId;
  private String eventName;
  private Long triggerTime;
  private String eventData;

}
