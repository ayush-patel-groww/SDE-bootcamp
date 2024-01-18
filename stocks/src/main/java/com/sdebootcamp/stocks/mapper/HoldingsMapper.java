package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.HoldingsDto;
import com.sdebootcamp.stocks.entity.Holdings;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface HoldingsMapper {
  Holdings HoldingsDtoToHoldings(HoldingsDto holdingsDto);
  HoldingsDto HoldingsToHoldingsDto(Holdings holdings);
  List<Holdings> HoldingsDtoListToHoldingsList(List<HoldingsDto> holdingsDtoList);
  List<HoldingsDto> HoldingsListToHoldingsDtoList(List<Holdings> holdingsList);

}

