package com.sdebootcamp.stocks.dto;

import java.util.List;
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
public class PortfolioDto {

  private Long holdingId;
  private Long userAccountId;
  private Long stockId;
  private String stockName;
  private double quantity;
  private double buyPrice;
  private double currentPrice;
  private double gainLoss;
  private boolean isBuy;

  private double totalPortfolioHolding;
  private double totalBuyPrice;
  private double totalProfitLoss;
  private double totalProfitLossPercentage;

}
