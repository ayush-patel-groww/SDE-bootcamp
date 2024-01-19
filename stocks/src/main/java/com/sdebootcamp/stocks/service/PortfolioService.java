package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Stocks;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import java.util.List;


public interface PortfolioService {
   PortfolioDto getPortfolioDetailsByUserId(Long userAccountId) throws Exception;
   void updatePortfolioDetailsAfterTrades(TradesDto tradesDto,StocksDto stocksDto) throws StockNotFound;

}
