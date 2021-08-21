package org.example;



import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.*;

import static spark.Spark.*;

/**
 * Minimal web app example for Heroku using SparkWeb
 *
 * @author daniel
 */
public class App {

    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        staticFiles.location("/public");
        port(getPort());
        //get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
        get("/facadealpha","application/json",(req, res) -> facadeAlpha(req, res));
        get("/iexapis","application/json",(req, res) -> iexapis(req, res));
    }

    private static String facadeAlpha(Request req, Response res) {
        String response ="None";
        try {
            response= HttpStockService.getService().getStockInfoAsJSON(req);
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,e);
        }
        return response;
    }
    private static String iexapis(Request req, Response res) {
        String response ="None";
        try {
            response= HttpStockService.getService().getStockInfoAsJSONiex(req);
        } catch (IOException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,e);
        }
        return response;
    }

    /**private static String inputDataPage(Request req, Response res) {
        String pageContent

        return pageContent;
    }**/

    private static String resultsPage(Request req, Response res) {
        return req.queryParams("firstname") + " " +
                req.queryParams("lastname");
    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    private static String getStockInfo(Request req,Response res){
        res.type("application/json");
        String responseStr = "none";
        try{
            HttpStockService stockService = HttpStockService.createService();
            responseStr = stockService.getStockInfoAsJSON(req);
        }
        catch (IOException ex){
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,null,ex);
        }
        return responseStr;
    }
    
}


