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
@Table(name = "Stock_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stocks {
  @Id
  @Column(nullable = false)
  private Long stockId;
  @Column(nullable = false)
  private String stockName;
  @Column(nullable = false)
  private double open;
  @Column(nullable = false)
  private double close;
  @Column(nullable = false)
  private double low;
  @Column(nullable = false)
  private double high;
  @Column(nullable = false)
  private double currentPrice;

}
