package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Trades;
import org.mapstruct.Mapper;

@Mapper
public interface TradeMapper {
  Trades tradesDtoToTrades(TradesDto tradesDto);
  TradesDto tradesToTradesDto(Trades trades);

}
