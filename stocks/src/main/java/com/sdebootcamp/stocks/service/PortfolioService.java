package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import java.util.List;


public interface PortfolioService {
   PortfolioDto getPortfolioDetailsByUserId(Long userAccountId);
   void updatePortfolioDetails(TradesDto tradesDto);

}
