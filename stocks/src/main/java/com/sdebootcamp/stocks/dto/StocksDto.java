package com.sdebootcamp.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksDto {
  private Long stockId;
  private String stockName;
  private double open;
  private double close;
  private double low;
  private double high;
  private double currentPrice;

}
