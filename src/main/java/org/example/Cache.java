package org.example;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private ConcurrentHashMap<String, String> stocks = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> Cloud = new ConcurrentHashMap<>();


    public void addStocks(String name ,String stock){
        stocks.put(name,stock);
    }


    public String getSocks(String name) throws IOException {

        if(stocks.containsKey(name)) {

            return stocks.get(name);
        }
        return "none";
    }
    public void addCloud(String name ,String stock){
        Cloud.put(name,stock);
    }


    public String getCloud(String name) throws IOException {
        if(Cloud.containsKey(name)) {
            return Cloud.get(name);
        }
        return "none";
    }
}
