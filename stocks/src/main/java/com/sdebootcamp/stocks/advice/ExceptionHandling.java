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
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ErrorMessage {

    private String errorMessage;
  }

  @ExceptionHandler(UserNotFound.class)
  ResponseEntity<ErrorMessage> handleUserNotFoundError(UserNotFound ex) {
    return ResponseEntity.status(404).body(ErrorMessage.builder().errorMessage(ex.getMessage()).build());
  }

  @ExceptionHandler(StockNotFound.class)
  ResponseEntity<ErrorMessage> handleStockNotFoundError(StockNotFound ex) {
    return ResponseEntity.status(404).body(ErrorMessage.builder().errorMessage(ex.getMessage()).build());
  }
  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
    return ResponseEntity.status(400).body(ErrorMessage.builder().errorMessage(ex.getMessage()).build());
  }
}
