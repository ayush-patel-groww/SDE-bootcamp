package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import java.util.List;

public interface TradeService {

  String placeOrder(TradesDto tradesDto) throws StockNotFound;

  List<TradesDto> getTradesByUserId(Long userId);

  List<TradesDto> getTradesByUserIdAndStockId(Long userId, Long stockId);

  List<TradesDto> getAllTrades();
}
