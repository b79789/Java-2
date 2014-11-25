package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private OnListItemClickListener mListener;
    public static MyListFragment newInstance() {
        MyListFragment frag = new MyListFragment();
        return frag;
    }

    public interface OnListItemClickListener{

        //public void displayText(String myText);
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
