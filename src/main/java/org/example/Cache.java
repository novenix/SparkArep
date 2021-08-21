package org.example;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private ConcurrentHashMap<String, String> stocks = new ConcurrentHashMap<>();


    /**

     */
    public void addStocks(String name ,String stock){
        stocks.put(name,stock);
    }

    /**
     *
     *
     */
    public String getSocks(String name) throws IOException {

        if(stocks.containsKey(name)) {

            return stocks.get(name);
        }
        return "none";
    }
}
