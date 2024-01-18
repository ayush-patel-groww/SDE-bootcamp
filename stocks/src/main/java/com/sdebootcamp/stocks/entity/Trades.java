package com.sdebootcamp.stocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Trades")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trades {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long tradeId;

  @Column(name = "user_id", nullable = false)
  private Long userAccountId;
  @Column(name = "stock_id", nullable = false)
  private Long stockId;
  @Column(nullable = false)
  private double quantity;
  private String stockName;
  private double currentPrice;
  private boolean isBuy;

}
