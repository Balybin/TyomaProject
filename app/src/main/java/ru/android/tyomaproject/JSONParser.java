package ru.android.tyomaproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    public List<Data> getData(String response) throws JSONException {
        JSONArray dataJson = new JSONArray(response);
        List<Data> dataArray = new ArrayList<>();
        for (int i = 0; i < dataJson.length(); i++) {
            JSONObject jsonItem = dataJson.getJSONObject(i);
            String Title = jsonItem.getString("title");
            String Body = jsonItem.getString("body");
            Data buf = new Data(Title, Body);
            dataArray.add(buf);
        }
        return dataArray;
    }
}
