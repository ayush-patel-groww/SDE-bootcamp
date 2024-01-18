package com.sdebootcamp.stocks.mapper;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.entity.Portfolio;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface PortfolioMapper {
    Portfolio portfolioDtoToPortfolio(PortfolioDto portfolioDto);
    PortfolioDto portfolioToPortfolioDto(Portfolio portfolio);

    List<Portfolio> portfolioDtoListToPortfolioList(List<PortfolioDto> portfolioDtoList);
    List<PortfolioDto> portfolioListToPortfolioDtoList(List<Portfolio> portfolioList);
}
