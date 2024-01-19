package com.sdebootcamp.stocks.controller;

import com.sdebootcamp.stocks.advice.ResponseMessage;
import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.service.StocksHelper;
import com.sdebootcamp.stocks.service.StocksService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

  @Autowired
  private StocksService stocksService;


  @PostMapping
  ResponseEntity<ResponseMessage> updateStocks(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (StocksHelper.hasCSVFormat(file)) {
      try {
        message = "Successfully uploaded the file :" + file.getOriginalFilename();
        new Thread(()->{
          try{
            stocksService.updateStocks(file);
          }
          catch (IOException ex){
            System.out.println(ex.getMessage());
          }
        }).start();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
            .body(new ResponseMessage(message));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  @GetMapping
  ResponseEntity<List<StocksDto>> getAllStocks() {
    try {
      List<StocksDto> stocksDtoList = stocksService.getAllStocks();
      if (stocksDtoList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(stocksDtoList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("{stockId}")
  ResponseEntity<StocksDto> getStockByStockId(@PathVariable Long stockId) throws StockNotFound {
     StocksDto stocksDto = stocksService.getStockByStockId(stockId);
    return new ResponseEntity<>(stocksDto, HttpStatus.OK);
  }

}
