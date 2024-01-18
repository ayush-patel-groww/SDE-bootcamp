package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Holdings;
import java.util.List;

public interface HoldingsService {
  List<HoldingsDto> getAllHoldingsByUserId(Long userId);
  List<HoldingsDto> getAllHoldingsByStockId(Long stockId);

  HoldingsDto getHoldingsByUserIdAndStockId(Long userId, Long stockId);

  void updateHoldingsAfterTrade(TradesDto tradesDto);
  void updateHoldingsAfterStocksUpdated(StocksDto stocksDto);

}
