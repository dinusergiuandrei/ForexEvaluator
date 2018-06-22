import org.json.JSONObject;

public class JsonHandler {
    final Integer indentFactor = 4;

    public JSONObject getJsonFromString(String source){
        if(source.startsWith("[")){
            source = "{\"data\":" + source + "}";
        }
        JSONObject jsonObject = new JSONObject(source);

        return jsonObject;
    }

    public void displayJson(JSONObject jsonObject){
        System.out.println(jsonObject.toString(indentFactor));
    }
}
