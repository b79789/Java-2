package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brianstacks.fragmentandfilefundamentals.R;

/**
 * Created by Brian Stacks
 * on 11/24/14
 * for FullSail.edu.
 */
public class MyListFragment extends ListFragment {

    public static final String TAG = "MyListFragment.TAG";

    public static MyListFragment newInstance() {
        MyListFragment frag = new MyListFragment();
        return frag;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        String[] teams = getResources().getStringArray(R.array.teamList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, teams);
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        String team = (String)_l.getItemAtPosition(_position);
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.team)
                .setMessage(getString(R.string.selected, team))
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}
