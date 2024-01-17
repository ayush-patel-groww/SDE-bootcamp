package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import java.util.List;


public interface PortfolioService {
   List<PortfolioDto> getPortfolioDetailsByUserId(Long userAccountId);
   PortfolioDto getPortfolioDetailsByUserIdAndStockId(Long userAccountId, Long stockId);
   void updatePortfolioDetails(TradesDto tradesDto);

}
