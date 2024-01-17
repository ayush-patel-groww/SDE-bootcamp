package com.sdebootcamp.stocks.advice;

import com.sdebootcamp.stocks.exceptions.StockNotFound;
import com.sdebootcamp.stocks.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class ErrorMessage {

    private String errorMessage;
  }

  @ExceptionHandler(UserNotFound.class)
  ResponseEntity<ErrorMessage> handleUserNotFoundError(UserNotFound ex) {
    return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
  }

  @ExceptionHandler(StockNotFound.class)
  ResponseEntity<ErrorMessage> handleStockNotFoundError(StockNotFound ex) {
    return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
  }
}
