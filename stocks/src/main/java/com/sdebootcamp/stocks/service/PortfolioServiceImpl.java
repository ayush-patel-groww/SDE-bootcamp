package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.mapper.PortfolioMapper;
import com.sdebootcamp.stocks.repository.PortfolioRepository;
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

  PortfolioDto getPortfolioDetailsByUserId(Long userId){
    return portfolioMapper.portfolioToPortfolioDto(portfolioRepository.findByUserId(userId));
  }

  void updatePortfolioDetails(TradesDto tradesDto){

  }

}
