package org.gojo.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @jakarta.persistence.Column(name = "id", nullable = false, updatable = false)
  @JsonProperty(value = "id")
  private Long id;

  @Version
  @jakarta.persistence.Column(name = "version")
  @JsonIgnore
  private Long version;

  @jakarta.persistence.Column(name = "created_at")
  @JsonProperty("created_at")
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @JsonProperty("updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;

}