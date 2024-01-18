package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Holdings;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.HoldingsMapper;
import com.sdebootcamp.stocks.repository.HoldingsRepository;
import java.util.List;
import java.util.Objects;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoldingsServiceImpl implements HoldingsService{

  @Autowired
  private HoldingsRepository holdingsRepository;

  private final HoldingsMapper holdingsMapper = Mappers.getMapper(HoldingsMapper.class);

  @Override
  public List<HoldingsDto> getAllHoldingsByUserId(Long userId) {
    return holdingsMapper.holdingsListToHoldingsDtoList(holdingsRepository.findByUserId(userId));
  }

  @Override
  public List<HoldingsDto> getAllHoldingsByStockId(Long stockId){
    return holdingsMapper.holdingsListToHoldingsDtoList(holdingsRepository.findByStockId(stockId));
  }
  @Override
  public HoldingsDto getHoldingsByUserIdAndStockId(Long userId, Long stockId) {
    return holdingsMapper.holdingsToHoldingsDto(
        holdingsRepository.findByUserIDAndStockId(userId, stockId));
  }

  @Override
  public void updateHoldingsAfterTrade(TradesDto tradesDto) throws StockNotFound {
    Holdings holdings = holdingsRepository.findByUserIDAndStockId(tradesDto.getUserAccountId(),
        tradesDto.getStockId());
    if (!Objects.equals(holdings.getUserAccountId(), tradesDto.getUserAccountId())) {
      holdingsRepository.save(Holdings.builder()
          .userAccountId(tradesDto.getUserAccountId())
          .stockId(tradesDto.getStockId())
          .stockName(tradesDto.getStockName())
          .quantity(tradesDto.getQuantity())
          .buyPrice(tradesDto.getCurrentPrice())
          .currentPrice(tradesDto.getCurrentPrice())
          .gainLoss(0.0)
          .build());
    }
    // Selling stocks
    if (!tradesDto.isBuy()) {
      holdings.setQuantity(holdings.getQuantity() - tradesDto.getQuantity());
      double gainLossPerStock = tradesDto.getCurrentPrice() - holdings.getBuyPrice();
      double gainLoss = gainLossPerStock * tradesDto.getQuantity();
      holdings.setGainLoss(holdings.getGainLoss() - gainLoss);
      holdingsRepository.save(holdings);
      return;
    }
    // Buying stocks
    double quantity = holdings.getQuantity() + tradesDto.getQuantity();
    holdings.setQuantity(quantity);
    double totalBuyPrice = holdings.getQuantity() * holdings.getBuyPrice()
        + tradesDto.getQuantity() * tradesDto.getCurrentPrice();
    double buyPrice = totalBuyPrice / quantity;
    holdings.setBuyPrice(buyPrice);
    holdingsRepository.save(holdings);

  }

  @Override
  public void updateHoldingsAfterStocksUpdated(StocksDto stocksDto){
    List<HoldingsDto> holdingsDtoList = holdingsMapper.holdingsListToHoldingsDtoList(holdingsRepository.findByStockId(
        stocksDto.getStockId()));
    for(HoldingsDto holdingsDto : holdingsDtoList){
      holdingsDto.setCurrentPrice(stocksDto.getCurrentPrice());
      holdingsDto.setGainLoss(holdingsDto.getQuantity()*(stocksDto.getCurrentPrice()-holdingsDto.getBuyPrice()));
      holdingsRepository.save(holdingsMapper.holdingsDtoToHoldings(holdingsDto));
    }
  }

}
