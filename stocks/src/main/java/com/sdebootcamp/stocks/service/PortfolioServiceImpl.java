package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.PortfolioMapper;
import com.sdebootcamp.stocks.repository.PortfolioRepository;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService{
  @Autowired
  private PortfolioRepository portfolioRepository;
//  @Autowired
//  private StocksService stocksService;
  @Autowired
  private HoldingsService holdingsService;

  private final PortfolioMapper portfolioMapper = Mappers.getMapper(PortfolioMapper.class);

  @Override
  public PortfolioDto getPortfolioDetailsByUserId(Long userId){
    List<HoldingsDto> holdingsDtoList = holdingsService.getAllHoldingsByUserId(userId);
    double totalPortfolioHolding=0.0;
    double totalBuyPrice = 0.0;
    double totalProfitLoss = 0.0;
    double totalProfitLossPercentage = 0.0;
    for(HoldingsDto holdingsDto: holdingsDtoList){
      totalPortfolioHolding += holdingsDto.getQuantity()* holdingsDto.getCurrentPrice();
      totalBuyPrice += holdingsDto.getQuantity()*holdingsDto.getBuyPrice();
      totalProfitLoss += holdingsDto.getGainLoss();
    }
    totalProfitLossPercentage = (totalProfitLoss/totalBuyPrice)*100;
    Portfolio portfolio = portfolioRepository.findByUserId(userId);
    portfolio.setTotalPortfolioHolding(totalPortfolioHolding);
    portfolio.setTotalBuyPrice(totalBuyPrice);
    portfolio.setTotalProfitLoss(totalProfitLoss);
    portfolio.setTotalProfitLossPercentage(totalProfitLossPercentage);
    portfolioRepository.save(portfolio);

    return portfolioMapper.portfolioToPortfolioDto(portfolio);
  }

  @Override
  public void updatePortfolioDetailsAfterTrades(TradesDto tradesDto,StocksDto stocksDto) throws StockNotFound{
    Portfolio portfolio = portfolioRepository.findByUserId(tradesDto.getUserAccountId());
//    StocksDto stocksDto = stocksService.getStockByStockId(tradesDto.getStockId());
    HoldingsDto holdingsDto = holdingsService.getHoldingsByUserIdAndStockId(
        tradesDto.getUserAccountId(), tradesDto.getStockId());

    // First time buying stocks
    if(!Objects.equals(portfolio.getUserAccountId(), tradesDto.getUserAccountId())){
      portfolioRepository.save(Portfolio.builder()
              .totalPortfolioHolding(tradesDto.getQuantity()*stocksDto.getCurrentPrice())
              .totalBuyPrice(tradesDto.getQuantity()*stocksDto.getCurrentPrice())
              .totalProfitLoss(0.0)
              .totalProfitLossPercentage(0.0)
          .build());
      return ;
    }

    // Selling stocks
    if(!tradesDto.isBuy()){
      double totalPortfolioHolding = portfolio.getTotalPortfolioHolding() - (tradesDto.getQuantity()*stocksDto.getCurrentPrice());
      portfolio.setTotalPortfolioHolding(totalPortfolioHolding);
      double totalBuyPrice = portfolio.getTotalBuyPrice()-(tradesDto.getQuantity()*holdingsDto.getBuyPrice());
      portfolio.setTotalBuyPrice(totalBuyPrice);
      double gainLossPerShare = holdingsDto.getGainLoss()/holdingsDto.getQuantity();
      double totalProfitLoss = portfolio.getTotalProfitLoss() - (gainLossPerShare*tradesDto.getQuantity());
      portfolio.setTotalProfitLoss(totalProfitLoss);
      double totalProfitLossPercentage = (totalProfitLoss/totalBuyPrice) * 100;
      portfolio.setTotalProfitLossPercentage(totalProfitLossPercentage);
      portfolioRepository.save(portfolio);
      return ;
    }
    // Buying stocks
    double totalPortfolioHolding = portfolio.getTotalPortfolioHolding() + (tradesDto.getQuantity()*stocksDto.getCurrentPrice());
    portfolio.setTotalPortfolioHolding(totalPortfolioHolding);
    double totalBuyPrice = portfolio.getTotalBuyPrice()+(tradesDto.getQuantity()*stocksDto.getCurrentPrice());
    portfolio.setTotalBuyPrice(totalBuyPrice);
    double totalProfitLossPercentage = (portfolio.getTotalProfitLoss()/totalBuyPrice) * 100;
    portfolio.setTotalProfitLossPercentage(totalProfitLossPercentage);
    portfolioRepository.save(portfolio);

  }



}
