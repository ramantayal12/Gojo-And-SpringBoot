package org.example.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Builder
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;
  private String userName;
  private String userEmail;

}
