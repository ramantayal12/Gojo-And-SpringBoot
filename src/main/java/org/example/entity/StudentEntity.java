package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data // combines features of @Getter and @Setter
@Builder
@Entity
@Table(name = "student_entity")
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity implements Serializable {

  @Id
  private Long id;
  private String name;
  private String email;
  private String contact;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
