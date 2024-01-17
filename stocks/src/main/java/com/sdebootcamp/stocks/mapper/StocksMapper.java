package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import org.mapstruct.Mapper;

@Mapper
public interface StocksMapper {
   Stocks StocksDtoToStocks (StocksDto stocksDto);
   StocksDto StocksToStocksDto (Stocks stocks);

}
