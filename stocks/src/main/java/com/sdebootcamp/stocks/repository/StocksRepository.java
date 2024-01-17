package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stocks, Long> {


}
