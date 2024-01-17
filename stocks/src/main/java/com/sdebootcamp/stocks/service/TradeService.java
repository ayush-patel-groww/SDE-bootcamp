package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.TradesDto;
import java.util.List;

public interface TradeService {

  String placeOrder(TradesDto tradesDto);

  List<TradesDto> getTradesByUserId(Long userId);

  List<TradesDto> getTradesByUserIdAndStockId(Long userId, Long stockId);

  List<TradesDto> getAllTrades();
}
