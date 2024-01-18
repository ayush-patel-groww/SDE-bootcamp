package com.sdebootcamp.stocks.controller;

import com.sdebootcamp.stocks.dto.PortfolioDto;
import com.sdebootcamp.stocks.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portfolio/")
public class PortfolioController {
  @Autowired
  private PortfolioService portfolioService;

  @GetMapping("{id}")
  ResponseEntity<PortfolioDto> getPortfolioDetailsByUserId(@PathVariable(name = "id") Long userAccountId){
    return new ResponseEntity<>(portfolioService.getPortfolioDetailsByUserId(userAccountId),
        HttpStatus.OK);
  }

}
