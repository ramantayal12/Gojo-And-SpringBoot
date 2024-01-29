package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long studentId;
  private String studentName;
  private String personalEmail;
  private String contactNumber;

}
