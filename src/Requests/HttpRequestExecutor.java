package Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static Utils.Strings.getStringFromBufferedReader;

public class HttpRequestExecutor {
    public String get(String urlString) throws IOException {
        return getContentFromConnection(createConnectionForGet(urlString));
    }

    private HttpURLConnection createConnectionForGet(String urlString) throws IOException {
        HttpURLConnection connection = this.createConnection(urlString);
        connection.setRequestMethod("GET");
        return connection;
    }

    private HttpURLConnection createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }

    private String getContentFromConnection(HttpURLConnection connection) throws IOException {
        BufferedReader rd = this.getReaderFromConnection(connection);
        return getStringFromBufferedReader(rd);
    }

    private BufferedReader getReaderFromConnection(HttpURLConnection connection) throws IOException {
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}
