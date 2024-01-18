package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.entity.Trades;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.TradeMapper;
import com.sdebootcamp.stocks.repository.TradesRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
  @Autowired
  private TradesRepository tradesRepository;
  @Autowired
  private HoldingsService holdingsService;
  @Autowired
  private PortfolioService portfolioService;
  @Autowired
  private StocksService stocksService;


  private final TradeMapper tradeMapper = Mappers.getMapper(TradeMapper.class);
  public String placeOrder(TradesDto tradesDto) throws StockNotFound {
    boolean isBuy = tradesDto.isBuy();
    double quantity = tradesDto.getQuantity();
    HoldingsDto holdingsDto = holdingsService.getHoldingsByUserIdAndStockId(tradesDto.getUserAccountId(),tradesDto.getStockId());
    if(!isBuy){
      if(holdingsDto.getQuantity()<quantity){
        return "Your account doesn't have required shares to sell";
      }
      tradesRepository.save(tradeMapper.tradesDtoToTrades(tradesDto));
    }
    StocksDto stocksDto = stocksService.getStockByStockId(tradesDto.getStockId());
    tradesRepository.save(tradeMapper.tradesDtoToTrades(tradesDto));
    holdingsService.updateHoldingsAfterTrade(tradesDto);
    portfolioService.updatePortfolioDetailsAfterTrades(tradesDto,stocksDto);
    return "Order Placed SuccessFully";
  }

 public List<TradesDto> getTradesByUserId(Long userId){
    List<Trades> tradesList = tradesRepository.findByUserId(userId);
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.tradesToTradesDto(trade));
    }
    return tradesDtoList;
  }

  public List<TradesDto> getTradesByUserIdAndStockId(Long userId, Long stockId){
    List<Trades> tradesList = tradesRepository.findByUserIdAndStockId(userId, stockId);
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.tradesToTradesDto(trade));
    }
    return tradesDtoList;
  }

  public List<TradesDto> getAllTrades(){
    List<Trades> tradesList = tradesRepository.findAll();
    List<TradesDto> tradesDtoList = new ArrayList<>();
    for(Trades trade : tradesList){
      tradesDtoList.add(tradeMapper.tradesToTradesDto(trade));
    }
    return tradesDtoList;
  }


}
