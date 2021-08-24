package org.example;




import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpStockService {
    private  static Cache cache = new Cache();
    private static  final HttpStockService _instance = createService();

    private static final String USER_AGENT = "Mozilla/5.0";

    public static HttpStockService getService(){
        return  _instance;
    }

    public static HttpStockService createService(){
        return new AlphaAdvantageHttpStockService();
    }

    public  String getStockInfoAsJSON(Request req) throws IOException {

        StockEnum time = StockEnum.valueOf(req.queryParams("time"));
        String stock = req.queryParams("stock");
        String responseStr = "None";
        String responseCache= String.valueOf(cache.getSocks(getURL(time,stock)));
        URL obj ;
        if(!responseCache.equals( "none")){
            obj = new URL(String.valueOf(cache.getSocks(getURL(time,stock))));

        }
        else{
            obj = new URL(getURL(time,stock));
            cache.addStocks(String.valueOf(obj),getURL(time,stock));
        }


        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseStr = response.toString();


            // print result
            System.out.println(responseStr);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return responseStr;
    }

    public  String getStockInfoAsJSONiex(Request req) throws IOException {

        StockEnum time = StockEnum.valueOf("INTRADAY");
        String stock = req.queryParams("stock");
        String responseStr = "None";
        String responseCache= String.valueOf(cache.getCloud(getURL(time,stock)));
        URL obj ;
        //URL obj = new URL(getURL(time,stock));
        if(!responseCache.equals( "none")){
            obj = new URL(String.valueOf(cache.getCloud(getURL(time,stock))));

        }
        else{
            obj = new URL(getURL(time,stock));
            cache.addCloud(String.valueOf(obj),getURL(time,stock));
        }
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseStr = response.toString();


            // print result
            System.out.println(responseStr);
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return responseStr;
    }
    abstract public String getURL(StockEnum time,String stock);
}

