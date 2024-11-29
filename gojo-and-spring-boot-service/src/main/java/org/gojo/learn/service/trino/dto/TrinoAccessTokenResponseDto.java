package org.gojo.learn.service.trino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrinoAccessTokenResponseDto {

  @JsonProperty("access_token")
  private String accessToken;

  // TimeUnit : Seconds
  @JsonProperty("expires_in")
  private int expiresIn;

  // TimeUnit : Seconds
  @JsonProperty("refresh_expires_in")
  private int refreshExpiresIn;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("id_token")
  private String idToken;

  @JsonProperty("not_before_policy")
  private String notBeforePolicy;

  @JsonProperty("session_state")
  private String sessionState;

  @JsonProperty("scope")
  private String scope;

}
