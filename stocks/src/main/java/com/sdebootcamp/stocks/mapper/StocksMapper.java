package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface StocksMapper {
   Stocks stocksDtoToStocks(StocksDto stocksDto);
   StocksDto stocksToStocksDto(Stocks stocks);
   List<Stocks> stocksDtoListToStocksList(List<StocksDto> stocksDtoList);
   List<StocksDto> stocksListToStocksDtoList(List<Stocks> stocksList);


}
