package Formatter;

import Forex.CurrencyPair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    final Integer indentFactor = 4;

    public void displayJson(JSONObject jsonObject){
        System.out.println(jsonObject.toString(indentFactor));
    }

    public String fixJsonString(String jsonString){
        if(jsonString.startsWith("[")){
            jsonString = "{\"data\":" + jsonString + "}";
        }
        return jsonString;
    }

    public List<CurrencyPair> getCurrencyPairs(JSONObject jsonObject){
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray("data");
        array.forEach(
                pair -> {
                    JSONObject obj = (JSONObject) pair;
                    String symbol = obj.getString("symbol");
                    Double price = obj.getDouble("price");
                    Double ask = obj.getDouble("ask");
                    Double bid = obj.getDouble("bid");
                    Long timestamp = obj.getLong("timestamp");
                    currencyPairs.add(new CurrencyPair(symbol, price, ask, bid, timestamp));
                }
        );
        return currencyPairs;
    }
}
