package com.sdebootcamp.stocks.service;

import com.sdebootcamp.stocks.dto.StocksDto;
import com.sdebootcamp.stocks.entity.Stocks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

@Builder
public class StocksHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "SC_CODE", "SC_NAME", "OPEN", "HIGH","CLOSE","LOW","LAST" };

  public static boolean hasCSVFormat(MultipartFile file) {

    return TYPE.equals(file.getContentType());
  }

  public static List<Stocks> csvToStocksDto(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Stocks> stocksList = new ArrayList<Stocks>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Stocks stocks = Stocks.builder()
            .stockId(Long.valueOf(csvRecord.get("SC_CODE")))
            .stockName(csvRecord.get("SC_NAME"))
            .open(Double.parseDouble(csvRecord.get("OPEN")))
            .close(Double.parseDouble(csvRecord.get("CLOSE")))
            .low(Double.parseDouble(csvRecord.get("LOW")))
            .high(Double.parseDouble(csvRecord.get("HIGH")))
            .currentPrice(Double.parseDouble(csvRecord.get("LAST")))
            .build();
//        System.out.println(stocksDto);
        stocksList.add(stocks);
      }

      return stocksList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }


}
