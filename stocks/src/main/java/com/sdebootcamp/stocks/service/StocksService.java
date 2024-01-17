package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface StocksService {
  Optional<StocksDto> getStockByStockId(Long stockId) throws StockNotFound;
  void updateStocks(MultipartFile multipartFile) throws IOException;

  List<StocksDto> getAllStocks();

}
