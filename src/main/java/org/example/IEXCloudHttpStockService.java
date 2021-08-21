package org.example;


public class IEXCloudHttpStockService extends HttpStockService{
    @Override
    public String getURL(StockEnum time,String stock) {
        return "https://cloud.iexapis.com/stable/stock/"+stock+"/quote?&token=pk_652760a048ca4559af32687bf0804fd6";
    }
}