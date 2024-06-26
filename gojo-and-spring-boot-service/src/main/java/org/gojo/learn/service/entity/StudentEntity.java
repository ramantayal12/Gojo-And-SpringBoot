package org.gojo.learn.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gojo.learn.service.entity.base.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data // combines features of @Getter and @Setter
@Builder
@Entity
@Table(name = "student_entity")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity extends BaseEntity implements Serializable {

  private String sid;
  private String name;
  private String email;
  private String contact;

}
