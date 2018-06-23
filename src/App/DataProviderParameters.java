package App;

import Utils.Strings;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class DataProviderParameters {
    private String symbolsSourceFilePath;
    private String protocol;
    private String host;
    private String apiKey;
    private String version;

    public DataProviderParameters(String appDataSourceFilePath) {
        try {
            loadAppDataFromJsonFile(appDataSourceFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Could not load app data from file: " + appDataSourceFilePath);
        }
    }

    private void loadAppDataFromJsonFile(String appDataSourceFilePath) throws FileNotFoundException {
        String source = Strings.getStringFromFile(appDataSourceFilePath);
        JSONObject jsonObject = new JSONObject(source);
        symbolsSourceFilePath = jsonObject.getString("symbolsSourceFilePath");
        protocol = jsonObject.getString("protocol");
        host = jsonObject.getString("host");
        apiKey = jsonObject.getString("apiKey");
        version = jsonObject.getString("version");
    }

    public String getSymbolsSourceFilePath() {
        return symbolsSourceFilePath;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getVersion() {
        return version;
    }
}
