package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Trades;

public interface TradeMapper {
  Trades TradesDtoToTrades(TradesDto tradesDto);
  TradesDto TradesToTradesDto(Trades trades);

}
