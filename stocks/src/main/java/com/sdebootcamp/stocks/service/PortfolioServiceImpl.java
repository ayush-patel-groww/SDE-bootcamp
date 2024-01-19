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
import java.util.Optional;
import javax.sound.sampled.Port;
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

  @Autowired
  private HoldingsService holdingsService;

  private final PortfolioMapper portfolioMapper = Mappers.getMapper(PortfolioMapper.class);

  @Override
  public PortfolioDto getPortfolioDetailsByUserId(Long userId) throws Exception{
    List<HoldingsDto> holdingsDtoList = holdingsService.getAllHoldingsByUserId(userId);
    if(Objects.isNull(holdingsDtoList)) throw new Exception("user not found");
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
    Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUserId(userId);
    if(optionalPortfolio.isEmpty()){
       Portfolio newPortfolio = Portfolio.builder()
           .totalProfitLoss(totalProfitLoss)
           .totalBuyPrice(totalBuyPrice)
           .userAccountId(userId)
           .totalPortfolioHolding(totalPortfolioHolding)
           .totalProfitLossPercentage(totalProfitLossPercentage)
           .build();
      portfolioRepository.save(newPortfolio);
       PortfolioDto newPortfolioDto = portfolioMapper.portfolioToPortfolioDto(newPortfolio);
       newPortfolioDto.setHoldingsDtoList(holdingsDtoList);
       return newPortfolioDto;
    }
    Portfolio portfolio = optionalPortfolio.get();
    portfolio.setTotalPortfolioHolding(totalPortfolioHolding);
    portfolio.setTotalBuyPrice(totalBuyPrice);
    portfolio.setTotalProfitLoss(totalProfitLoss);
    portfolio.setTotalProfitLossPercentage(totalProfitLossPercentage);
    portfolioRepository.save(portfolio);

     PortfolioDto portfolioDto = portfolioMapper.portfolioToPortfolioDto(portfolioRepository.findByUserId(userId).orElseGet(()->{return Portfolio.builder()
        .build();}));
     portfolioDto.setHoldingsDtoList(holdingsDtoList);
     return portfolioDto;

  }

  @Override
  public void updatePortfolioDetailsAfterTrades(TradesDto tradesDto,StocksDto stocksDto) throws StockNotFound{
    Optional<Portfolio> optionalPortfolio = portfolioRepository.findByUserId(tradesDto.getUserAccountId());

    HoldingsDto holdingsDto = holdingsService.getHoldingsByUserIdAndStockId(
        tradesDto.getUserAccountId(), tradesDto.getStockId());

    // First time buying stocks
    Portfolio portfolio = optionalPortfolio.orElseGet(()->{
         return portfolioRepository.save(Portfolio.builder()
                 .userAccountId(tradesDto.getUserAccountId())
          .totalPortfolioHolding(tradesDto.getQuantity()*stocksDto.getCurrentPrice())
          .totalBuyPrice(tradesDto.getQuantity()*stocksDto.getCurrentPrice())
          .totalProfitLoss(0.0)
          .totalProfitLossPercentage(0.0)
          .build());
    });

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
