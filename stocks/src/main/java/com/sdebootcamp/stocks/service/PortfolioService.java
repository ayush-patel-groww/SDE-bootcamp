package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;


public interface PortfolioService {
   PortfolioDto getPortfolioDetails(Long userAccountId);
   void updatePortfolioDetails(PortfolioDto portfolioDto);

}
