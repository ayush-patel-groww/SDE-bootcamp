package com.sdebootcamp.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradesDto {

  private Long tradeId;
  private Long userAccountId;
  private Long stockId;
  private double quantity;
  private boolean isBuy;

}
