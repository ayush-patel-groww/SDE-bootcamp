package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Portfolio;
import com.sdebootcamp.stocks.entity.Trades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
  @Query(value = "SELECT * FROM Portfolio WHERE user_id = ?1", nativeQuery = true)
  List<Portfolio> findByUserId(Long userId);

  @Query(value = "SELECT * FROM Portfolio WHERE user_id = ?1 AND stock_id = ?2", nativeQuery = true)
  Portfolio findByUserIdAndStockId(Long userId, Long stockId);

}
