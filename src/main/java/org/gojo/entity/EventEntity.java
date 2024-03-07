package org.gojo.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.redis.core.RedisHash;

@Data// contains both Getter/Setter
@Builder
@RedisHash("events_caching")
public class EventEntity {

  @Id
  private String eventId;
  private String eventName;
  private Long triggerTime;
  private String eventData;

}
