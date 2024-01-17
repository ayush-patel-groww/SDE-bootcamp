package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Stocks;
import com.sdebootcamp.stocks.entity.Trades;
import com.sdebootcamp.stocks.mapper.TradeMapper;
import com.sdebootcamp.stocks.repository.TradesRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TradeServiceImpl {

  private TradesRepository tradesRepository;
  private final TradeMapper tradeMapper = Mappers.getMapper(TradeMapper.class);
  String placeOrder(TradesDto tradesDto){
    tradesRepository.save(tradeMapper.TradesDtoToTrades(tradesDto));
    return "Order Placed SuccessFully";
  }

  List<TradesDto> getTradesByUserId(Long userId){
    List<Trades> tradesList = tradesRepository.findByUserId(userId);
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.TradesToTradesDto(trade));
    }
    return tradesDtoList;
  }

  List<TradesDto> getTradesByUserIdAndStockId(Long userId, Long stockId){
    List<Trades> tradesList = tradesRepository.findByUserIdAndStockId(userId, stockId);
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.TradesToTradesDto(trade));
    }
    return tradesDtoList;
  }

  List<TradesDto> getAllTrades(){
    List<Trades> tradesList = tradesRepository.findAll();
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.TradesToTradesDto(trade));
    }
    return tradesDtoList;
  }


}
