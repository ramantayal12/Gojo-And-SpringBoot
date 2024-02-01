package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
