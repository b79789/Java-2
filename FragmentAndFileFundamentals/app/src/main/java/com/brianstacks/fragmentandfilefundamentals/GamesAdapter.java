package com.brianstacks.fragmentandfilefundamentals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brian Stacks
 * on 11/25/14
 * for FullSail.edu.
 */
public class GamesAdapter extends ArrayAdapter<Games> {

    private Context context;
    private List<Games> gamesList;
    public GamesAdapter(Context context, int resource,List<Games> objects) {
        super(context, resource);
        this.context = context;
        this.gamesList =objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        View view;
        view = inflator.inflate(R.layout.item_place,null);
        Games games = gamesList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        TextView tv3 = (TextView) view.findViewById(R.id.textView3);
        tv.setText(games.getHome());
        tv2.setText(games.getAway());
        tv3.setText(games.getVenue());
        return  view;
    }
}
