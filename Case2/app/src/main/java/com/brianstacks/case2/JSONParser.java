package com.brianstacks.case2;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONParser {

    public static List<String> parseFeed(String content) {


        JSONObject myObj;
        try {
            myObj = new JSONObject(content);
            JSONObject result = myObj.getJSONObject("current_observation");
            //JSONArray result2 = result.getJSONArray("current_observation");
            List<String> gamesList = new ArrayList<>();

            Log.v("resultList:",result.toString());
           // for (int i = 0; i < result2.length(); i++) {

                //JSONObject obj = result2.getJSONObject(i);
                /*Games games = new Games();
                games.setHome(obj.getString("home"));
                games.setAways(obj.getString("away"));
                games.setVenue(obj.getJSONObject("venue").getString("name"));
                gamesList.add(games);

                */
           // }

            return gamesList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}