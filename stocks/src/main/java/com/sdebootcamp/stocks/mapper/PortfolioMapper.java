package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface PortfolioMapper {
    Portfolio PortfolioDtoToPortfolio(PortfolioDto portfolioDto);
    PortfolioDto PortfolioToPortfolioDto(Portfolio portfolio);

    List<Portfolio> PortfolioDtoListToPortfolioList(List<PortfolioDto> portfolioDtoList);
    List<PortfolioDto> PortfolioListToPortfolioDtoList(List<Portfolio> portfolioList);
}
