package org.gojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // contains both @Getter and @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

  private Long id;
  private String name;
  private String email;
  private String contact;

}
