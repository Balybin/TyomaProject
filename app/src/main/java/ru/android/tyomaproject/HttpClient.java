package ru.android.tyomaproject;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class HttpClient {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String GET = "GET";
    private final JSONParser jsonParser;

    public HttpClient() {
        jsonParser = new JSONParser();
    }

    public List<Data> readDataInfo(int id) throws IOException, JSONException {
        String requestUrl = "https://jsonplaceholder.typicode.com/posts?userId=";
        URL url = new URL(requestUrl + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty(HEADER_AUTHORIZATION, requestUrl + id);
        connection.connect();

        InputStream in;
        int status = connection.getResponseCode();
        if( status != HttpURLConnection.HTTP_OK) {
            in = connection.getErrorStream();
        }else{
            in = connection.getInputStream();
        }
        String response = convertStreamToString(in);
        List<Data> data = jsonParser.getData(response);
        return data;
    }

    private String convertStreamToString(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;

        while((line = reader.readLine()) != null){
            sb.append(line).append("\n");
        }
        in.close();

        return sb.toString();
    }
}
