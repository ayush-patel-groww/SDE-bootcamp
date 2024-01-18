package com.sdebootcamp.stocks.dto;

import com.sdebootcamp.stocks.entity.Holdings;
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

  private Long userAccountId;
  private List<Holdings> holdingsList;
  private double totalPortfolioHolding;
  private double totalBuyPrice;
  private double totalProfitLoss;
  private double totalProfitLossPercentage;
}
