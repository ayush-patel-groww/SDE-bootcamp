package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.entity.Holdings;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface HoldingsMapper {
  Holdings holdingsDtoToHoldings(HoldingsDto holdingsDto);
  HoldingsDto holdingsToHoldingsDto(Holdings holdings);
  List<Holdings> holdingsDtoListToHoldingsList(List<HoldingsDto> holdingsDtoList);
  List<HoldingsDto> holdingsListToHoldingsDtoList(List<Holdings> holdingsList);

}

