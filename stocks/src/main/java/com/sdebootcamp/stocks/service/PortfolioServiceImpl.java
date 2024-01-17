package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import com.sdebootcamp.stocks.entity.Trades;
import com.sdebootcamp.stocks.mapper.PortfolioMapper;
import com.sdebootcamp.stocks.repository.PortfolioRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioServiceImpl {
  @Autowired
  private PortfolioRepository portfolioRepository;

  private final PortfolioMapper portfolioMapper = Mappers.getMapper(PortfolioMapper.class);

  List<PortfolioDto> getPortfolioDetailsByUserId(Long userId){
    List<Portfolio> portfolioList = portfolioRepository.findByUserId(userId);
    List<PortfolioDto> portfolioDtoList = new ArrayList<>();
    for(Portfolio portfolio : portfolioList){
      portfolioDtoList.add(portfolioMapper.PortfolioToPortfolioDto(portfolio));
    }
    return portfolioDtoList;
  }

  PortfolioDto getPortfolioDetailsByUserIdAndStockId(Long userId, Long stockId){
    return portfolioMapper.PortfolioToPortfolioDto(portfolioRepository.findByUserIdAndStockId(userId, stockId));
  }

  void updatePortfolioDetails(TradesDto tradesDto){

  }

}
