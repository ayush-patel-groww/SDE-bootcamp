package com.sdebootcamp.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsersDto {
  private Long userAccountId;
  private String username;
  private String password;
//  private boolean isLoggedIn;

}
