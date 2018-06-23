package App;

import Database.SqliteDatabase;
import Forex.CurrencyPair;
import Forex.ForexUrlProvider;
import Formatter.JsonHandler;
import Requests.HttpRequestExecutor;
import Utils.Strings;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * https://1forge.com/forex-data-api/api-documentation
 */

public class DataProvider {

    public static void main(String args[]) throws IOException {

        String dataProviderParamsPath = "app_setup.json";
        DataProviderParameters dataProviderParameters = new DataProviderParameters(dataProviderParamsPath);

        ForexUrlProvider urlProvider = new ForexUrlProvider(
                dataProviderParameters.getSymbolsSourceFilePath(),
                dataProviderParameters.getProtocol(),
                dataProviderParameters.getHost(),
                dataProviderParameters.getApiKey(),
                dataProviderParameters.getVersion()
        );

        String url = urlProvider.buildCurrencyUrl(urlProvider.getSymbols());
        HttpRequestExecutor executor = new HttpRequestExecutor();
        JsonHandler jsonHandler = new JsonHandler();
        String result = jsonHandler.fixJsonString(executor.get(url));
        JSONObject jsonObject = new JSONObject(result);
        List<CurrencyPair> currencyPairs = jsonHandler.getCurrencyPairs(jsonObject);
        SqliteDatabase sqliteDatabase = new SqliteDatabase();
        currencyPairs.forEach(sqliteDatabase::insert);
    }


}
