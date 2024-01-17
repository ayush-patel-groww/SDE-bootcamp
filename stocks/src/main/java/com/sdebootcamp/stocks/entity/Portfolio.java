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
@Table(name = "Portfolio")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long holdingId;
  @Column(nullable = false)
  private Long userAccountId;
  @Column(nullable = false)
  private Long stockId;
  @Column(nullable = false)
  private String stockName;
  @Column(nullable = false)
  private double quantity;
  @Column(nullable = false)
  private double buyPrice;
  @Column(nullable = false)
  private double currentPrice;
  @Column(nullable = false)
  private double gainLoss;
  @Column(nullable = false)
  private String tradeType;

  private double totalPortfolioHolding;
  private double totalBuyPrice;
  private double totalProfitLoss;
  private double totalProfitLossPercentage;

}
