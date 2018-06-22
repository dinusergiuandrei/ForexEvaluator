import org.json.JSONException;
import org.json.JSONObject;

public class ForexEvaluator {
    static Integer indentFactor = 4;

    public static void main(String args[]) throws Exception {
        String url = "https://forex.1forge.com/1.0.3/quotes?pairs=EURUSD,GBPJPY,AUDUSD&api_key=6ZoVOilZcI7eSLeTNGc1BOup4EQPBYtP";
        String result = HttpRequestExecutor.get(url);
        if(result.startsWith("[")){
            result = "{\"data\":" + result + "}";
        }
        JSONObject jsonObject = new JSONObject(result);
        System.out.println(jsonObject.toString(indentFactor));
    }
}
