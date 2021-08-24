package org.example;

public class AlphaAdvantageHttpStockService extends HttpStockService {
    @Override
    public String getURL(String time,String stock) {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_"+time+"&symbol="+stock+"&interval=5min&apikey=IOT2RPB7RESV1CXE";
    }
}