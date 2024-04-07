package org.gojo.learn.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gojo.learn.entity.base.BaseEntity;

@Data // contains both @Getter and @Setter
@Entity // This tells Hibernate to make a table out of this class
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

  private long userId;
  private String userName;
  private String userEmail;

}
