package org.gojo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gojo.entity.base.BaseEntity;

@Data // contains both @Getter and @Setter
@Entity // This tells Hibernate to make a table out of this class
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  private long userId;
  private String userName;
  private String userEmail;

}
