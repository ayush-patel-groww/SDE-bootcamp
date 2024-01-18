package com.sdebootcamp.stocks.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
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
  private Long userAccountId;
  private double totalPortfolioHolding;
  private double totalBuyPrice;
  private double totalProfitLoss;
  private double totalProfitLossPercentage;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "holding_id")
  private List<Holdings> holdingsList;
}
