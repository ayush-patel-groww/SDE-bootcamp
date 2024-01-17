package com.sdebootcamp.stocks.service;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.StocksMapper;
import com.sdebootcamp.stocks.mapper.UserMapper;
import com.sdebootcamp.stocks.repository.StocksRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class StocksServiceImpl implements StocksService{
  @Autowired
  private StocksRepository stocksRepository;
  private final StocksMapper stocksMapper = Mappers.getMapper(StocksMapper.class);


  @Override
  public StocksDto getStockByStockId(Long stockId) throws StockNotFound {
     Optional<Stocks> optionalStock = stocksRepository.findById(stockId);
     if(optionalStock.isPresent()) return stocksMapper.StocksToStocksDto(optionalStock.get());
     throw new StockNotFound("Invalid Stock id pass"+stockId);
  }

  @Override
  public void updateStocks(MultipartFile multipartFile)  {
    try{
      List<StocksDto> stocksDtoList = StocksHelper.csvToStocksDto(multipartFile.getInputStream());
      List<Stocks> stocksList = new ArrayList<>();
      for(StocksDto stocksDto : stocksDtoList){
        stocksList.add(stocksMapper.StocksDtoToStocks(stocksDto));
      }
      stocksRepository.saveAll(stocksList);
    } catch (IOException e){
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  @Override
  public List<StocksDto> getAllStocks() {
    List<Stocks> stocksList = stocksRepository.findAll();
    List<StocksDto> stocksDtoList = new ArrayList<>();
    for(Stocks stock : stocksList){
      stocksDtoList.add(stocksMapper.StocksToStocksDto(stock));
    }
    return stocksDtoList;
  }
}
