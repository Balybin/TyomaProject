package ru.android.tyomaproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class JSONParser {
    public Collection<Data> getData(String response) throws JSONException {
        JSONArray dataJson = new JSONArray(response);
        Collection<Data> dataArray = new ArrayList<>();
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
