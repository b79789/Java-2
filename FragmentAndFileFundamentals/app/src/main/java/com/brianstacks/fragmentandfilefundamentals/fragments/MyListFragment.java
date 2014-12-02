package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.brianstacks.fragmentandfilefundamentals.Games;
import com.brianstacks.fragmentandfilefundamentals.GamesAdapter;
import com.brianstacks.fragmentandfilefundamentals.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Brian Stacks
 * on 11/24/14
 * for FullSail.edu.
 */
public class MyListFragment extends ListFragment {
    ArrayList<Games>  gameList;
    Spinner mySpin;
    public static final String GameList_Text = "DetailFragment.Arg_Text";
    public static final String TAG = "MyListFragment.TAG";
    private OnListItemClickListener mListener;
    public static MyListFragment newInstance(ArrayList<Games> gameList) {
        MyListFragment frag = new MyListFragment();
        Bundle args= new Bundle();
        args.putSerializable(GameList_Text, gameList);
        frag.setArguments(args);
        return frag;
    }

    public interface OnListItemClickListener{

        public void displayText(String myText);
    }

    @Override
    public void  onAttach(Activity activity){
        super.onAttach(activity);

        if (activity instanceof OnListItemClickListener){
            mListener = (OnListItemClickListener) activity;
        }else {
            throw new IllegalArgumentException("Containing Activity must implement the OnListItemClicked");
        }
    }
    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);
        return view;
    }

    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        Bundle args =getArguments();
        if (args != null && args.containsKey(GameList_Text)){
            getGameList(args.getSerializable(GameList_Text));
        }
        mySpin = (Spinner) getView().findViewById(R.id.myEditText);
        String[] spinnerNumbers = getResources().getStringArray(R.array.weeksList);
        if (mySpin != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spinnerNumbers);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpin.setAdapter(arrayAdapter);
        }
        String[] teams = getResources().getStringArray(R.array.teamList);
        GamesAdapter adapter2 = new GamesAdapter(this.getActivity(),R.layout.item_place,gameList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, teams);
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        String team = (String)_l.getItemAtPosition(_position);
        mListener.displayText(team);

    }

    public void getGameList(Serializable gameList) {


    }


}
