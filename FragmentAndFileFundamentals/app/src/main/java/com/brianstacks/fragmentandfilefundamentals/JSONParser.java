package com.brianstacks.fragmentandfilefundamentals;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONParser {

    public static List<Games> parseFeed(String content) {


        JSONObject myObj;
        try {
            myObj = new JSONObject(content);
            JSONArray result = myObj.getJSONArray("games");
            Log.v("List Objects:",result.toString());
            List<Games> gamesList = new ArrayList<>();

            for (int i = 0; i < result.length(); i++) {
                JSONObject obj = result.getJSONObject(i);
                Games games = new Games();
                games.setHome(obj.getString("home"));
                games.setAways(obj.getString("away"));
                games.setVenue(obj.getString("venue"));
                //place.setPhotos(obj.getString("photos"));

                gamesList.add(games);
            }
            return gamesList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}