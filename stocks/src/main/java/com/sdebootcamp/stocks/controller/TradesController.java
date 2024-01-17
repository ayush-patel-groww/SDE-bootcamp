package com.sdebootcamp.stocks.controller;

import com.sdebootcamp.stocks.dto.TradesDto;
import com.sdebootcamp.stocks.service.PortfolioService;
import com.sdebootcamp.stocks.service.TradeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trade")
public class TradesController {
  @Autowired
  private TradeService tradeService;
  @Autowired
  private PortfolioService portfolioService;

  @PostMapping
  public String placeOrder(@RequestBody TradesDto tradesDto){
    return tradeService.placeOrder(tradesDto);
  }

  @GetMapping
  public List<TradesDto> getTradesByUserId(@RequestParam(name="userId") Long userId){
    return tradeService.getTradesByUserId(userId);
  }

  @GetMapping
  public List<TradesDto> getTradesByUserIdAndStockID(@RequestParam(name="userId") Long userId, @RequestParam(name="stockId") Long stockId){
    return tradeService.getTradesByUserIdAndStockId(userId, stockId);
  }

}
