package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Holdings;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.HoldingsMapper;
import com.sdebootcamp.stocks.repository.HoldingsRepository;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class HoldingsServiceImpl {

  @Autowired
  private HoldingsRepository holdingsRepository;
  @Autowired
  private StocksService stocksService;
  @Autowired
  private PortfolioService portfolioService;
  @Autowired
  private HoldingsService holdingsService;

  private final HoldingsMapper holdingsMapper = Mappers.getMapper(HoldingsMapper.class);

  List<HoldingsDto> getAllHoldingsByUserId(Long userId) {
    return holdingsMapper.HoldingsListToHoldingsDtoList(holdingsRepository.findByUserId(userId));
  }

  List<HoldingsDto> getAllHoldingsByStockId(Long stockId){
    return holdingsMapper.HoldingsListToHoldingsDtoList(holdingsRepository.findByStockId(stockId));
  }

  HoldingsDto getHoldingsByUserIdAndStockId(Long userId, Long stockId) {
    return holdingsMapper.HoldingsToHoldingsDto(
        holdingsRepository.findByUserIDAndStockId(userId, stockId));
  }

  void updateHoldingsAfterTrade(TradesDto tradesDto) throws StockNotFound {
    Holdings holdings = holdingsRepository.findByUserIDAndStockId(tradesDto.getUserAccountId(),
        tradesDto.getStockId());
    StocksDto stocksDto = stocksService.getStockByStockId(tradesDto.getStockId());
    // first time buying stocks
    if (!Objects.equals(holdings.getUserAccountId(), tradesDto.getUserAccountId())) {
      holdingsRepository.save(Holdings.builder()
          .userAccountId(tradesDto.getUserAccountId())
          .stockId(tradesDto.getStockId())
          .stockName(stocksDto.getStockName())
          .quantity(tradesDto.getQuantity())
          .buyPrice(stocksDto.getCurrentPrice())
          .currentPrice(stocksDto.getCurrentPrice())
          .gainLoss(0.0)
          .build());
    }
    // Selling stocks
    if (!tradesDto.isBuy()) {
      holdings.setQuantity(holdings.getQuantity() - tradesDto.getQuantity());
      double gainLossPerStock = stocksDto.getCurrentPrice() - holdings.getBuyPrice();
      double gainLoss = gainLossPerStock * tradesDto.getQuantity();
      holdings.setGainLoss(holdings.getGainLoss() - gainLoss);
      holdingsRepository.save(holdings);
      return;
    }
    // Buying stocks
    double quantity = holdings.getQuantity() + tradesDto.getQuantity();
    holdings.setQuantity(quantity);
    double totalBuyPrice = holdings.getQuantity() * holdings.getBuyPrice()
        + tradesDto.getQuantity() * stocksDto.getCurrentPrice();
    double buyPrice = totalBuyPrice / quantity;
    holdings.setBuyPrice(buyPrice);
    holdingsRepository.save(holdings);

  }

  void updateHoldingsAfterStocksUpdated(StocksDto stocksDto){
    List<HoldingsDto> holdingsDtoList = holdingsService.getAllHoldingsByStockId(
        stocksDto.getStockId());
    for(HoldingsDto holdingsDto : holdingsDtoList){
      holdingsDto.setCurrentPrice(stocksDto.getCurrentPrice());
      holdingsDto.setGainLoss(holdingsDto.getQuantity()*(stocksDto.getCurrentPrice()-holdingsDto.getBuyPrice()));
      holdingsRepository.save(holdingsMapper.HoldingsDtoToHoldings(holdingsDto));
    }
  }

}
