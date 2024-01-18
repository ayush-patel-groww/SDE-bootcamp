package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Holdings;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingsRepository extends JpaRepository<Holdings, Long> {
  @Query(value = "SELECT * FROM Holdings WHERE user_id = ?1", nativeQuery = true)
  List<Holdings> findByUserId(Long userId);

  @Query(value = "SELECT * FROM Holdings WHERE stock_id = ?1", nativeQuery = true)
  List<Holdings> findByStockId(Long stockId);

  @Query(value = "SELECT * FROM Holdings WHERE user_id = ?1 AND stock_id = ?2", nativeQuery = true)
  Holdings findByUserIDAndStockId(Long userId, Long stockId);

}
