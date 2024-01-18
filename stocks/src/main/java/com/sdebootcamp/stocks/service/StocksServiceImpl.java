package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.mapper.StocksMapper;
import com.sdebootcamp.stocks.repository.StocksRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StocksServiceImpl implements StocksService{
  @Autowired
  private StocksRepository stocksRepository;
  private final StocksMapper stocksMapper = Mappers.getMapper(StocksMapper.class);


  @Override
  public StocksDto getStockByStockId(Long stockId) throws StockNotFound {
     Optional<Stocks> optionalStock = stocksRepository.findById(stockId);
     if(optionalStock.isPresent()) {
       return stocksMapper.StocksToStocksDto(optionalStock.get());
     }
     throw new StockNotFound("Invalid Stock id pass"+stockId);
  }

  @Override
  public void updateStocks(MultipartFile multipartFile) {
    try{

      List<Stocks> stocksList = StocksHelper.csvToStocksDto(multipartFile.getInputStream());

      for(Stocks stock : stocksList){

        Long stockId = stock.getStockId();
        Optional<Stocks> stockAlreadyPresent = stocksRepository.findById(stockId);
        if(stockAlreadyPresent.isPresent()){
          Stocks stockUpdate = stockAlreadyPresent.get();
          stockUpdate.setStockName(stock.getStockName());
          stockUpdate.setClose(stock.getClose());
          stockUpdate.setOpen(stock.getOpen());
          stockUpdate.setLow(stock.getLow());
          stockUpdate.setHigh(stock.getHigh());
          stockUpdate.setCurrentPrice(stock.getCurrentPrice());
          stocksRepository.save(stockUpdate);
        }
        else{
          stocksRepository.save(stock);
        }

      }

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
