package com.brianstacks.fragmentandfilefundamentals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Brian Stacks
 * on 11/25/14
 * for FullSail.edu.
 */
public class GamesAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x010101010L;
    private Context context;
    private ArrayList<Games> gamesList;
    public GamesAdapter(Context context, ArrayList<Games> objects) {
        this.context = context;
        this.gamesList =objects;
    }

    @Override
    public int getCount() {
        return gamesList.size();
    }

    @Override
    public Object getItem(int position) {
        return gamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflator = (LayoutInflater)context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        View view;
        view = inflator.inflate(R.layout.item_place,null);
        Games games = gamesList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        tv.setText(games.getHome());
        tv2.setText(games.getAway());
        return  view;
    }
}
