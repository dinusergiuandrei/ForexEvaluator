package App;

import Database.SqliteDatabase;
import Forex.CurrencyPair;
import Forex.ForexUrlProvider;
import Formatter.JsonHandler;
import Requests.HttpRequestExecutor;
import org.json.JSONObject;

import java.util.List;


public class ForexEvaluator {
    public static void main(String args[]) throws Exception {
        SqliteDatabase sqliteDatabase = new SqliteDatabase();
        sqliteDatabase.getBySymbol("USDAUD").forEach(pair -> System.out.println(pair.toString()));
    }
}
