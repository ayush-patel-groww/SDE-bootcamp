package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import org.mapstruct.Mapper;

@Mapper
public interface PortfolioMapper {
    Portfolio PortfolioDtoToPortfolio(PortfolioDto portfolioDto);
    PortfolioDto PortfolioToPortfolioDto(Portfolio portfolio);
}
