package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Trades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TradesRepository extends JpaRepository<Trades, Long> {
  @Query(value = "SELECT * FROM Trades WHERE user_id = ?1", nativeQuery = true)
  List<Trades> findByUserId(Long userId);

  @Query(value = "SELECT * FROM Trades WHERE user_id = ?1 AND stock_id = ?2", nativeQuery = true)
  List<Trades> findByUserIdAndStockId(Long userId, Long stockId);
}
