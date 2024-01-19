package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Portfolio;
import com.sdebootcamp.stocks.entity.Trades;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
  @Query(value = "SELECT * FROM Portfolio WHERE user_id = ?1", nativeQuery = true)
  Optional<Portfolio> findByUserId(Long userId);

}
