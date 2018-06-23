package Forex;

import Formatter.JsonHandler;
import Utils.Strings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ForexUrlProvider{
    private String symbolSourceFilePath;

    private List<String> symbols = new ArrayList<>();

    private String protocol;

    private String host;

    private String apiKey;

    private String version;

    public ForexUrlProvider(String symbolSourceFilePath, String protocol, String host, String apiKey, String version) {
        this.symbolSourceFilePath = symbolSourceFilePath;
        this.protocol = protocol;
        this.host = host;
        this.apiKey = apiKey;
        this.version = version;
        loadSymbolsFromFile();
    }

    public String buildQuotaUrl(){
        return this.protocol + "://" + this.host + "/" + this.version + "/quota?api_key=" + this.apiKey;
    }

    public String buildMarketStatusUrl(){
        return this.protocol + "://" + this.host + "/" + this.version + "/market_status?api_key=" + this.apiKey;
    }

    public String buildCurrencyUrl(List<String> symbols){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.protocol).append("://");
        stringBuilder.append(this.host).append("/").append(this.version).append("/quotes?pairs=");
        for (int i = 0; i < symbols.size(); i++) {
            stringBuilder.append(symbols.get(i));
            if(i<symbols.size()-1)
                stringBuilder.append(",");
        }
        stringBuilder.append("&api_key=").append(this.apiKey);
        return stringBuilder.toString();
    }

    public List<String> getSymbols() {
        return symbols;
    }

    private void loadSymbolsFromFile(){
        try {
            String text = Strings.getStringFromFile(symbolSourceFilePath);
            JsonHandler jsonHandler = new JsonHandler();
            String source = jsonHandler.fixJsonString(text);
            JSONObject jsonObject = new JSONObject(source);
            JSONArray array = jsonObject.getJSONArray("data");
            for (Object object : array) {
                String symbol = (String) object;
                this.symbols.add(symbol);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
