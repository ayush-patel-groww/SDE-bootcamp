package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import com.sdebootcamp.stocks.entity.Stocks;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface StocksMapper {
   Stocks StocksDtoToStocks (StocksDto stocksDto);
   StocksDto StocksToStocksDto (Stocks stocks);
   List<Stocks> StocksDtoListToStocksList(List<StocksDto> stocksDtoList);
   List<StocksDto> StocksListToStocksDtoList(List<Stocks> stocksList);


}
