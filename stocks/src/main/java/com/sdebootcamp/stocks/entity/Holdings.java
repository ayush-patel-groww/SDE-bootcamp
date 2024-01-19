package com.sdebootcamp.stocks.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Holdings")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holdings {

  @Id
  @Column(name = "holding_id",nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long holdingId;
  @Column(name ="user_id")
  private Long userAccountId;
  @Column(name = "stock_id")
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

}
