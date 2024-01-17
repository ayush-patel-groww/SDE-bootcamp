package com.sdebootcamp.stocks.repository;

import com.sdebootcamp.stocks.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

}
