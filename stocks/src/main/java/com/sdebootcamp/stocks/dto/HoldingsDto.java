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
public class HoldingsDto {

  private Long holdingId;
  private Long userAccountId;
  private Long stockId;
  private String stockName;
  private double quantity;
  private double buyPrice;
  private double currentPrice;
  private double gainLoss;

}