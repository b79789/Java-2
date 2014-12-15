package com.brianstacks.multi_activityapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.brianstacks.multi_activityapp.DataAdapter;
import com.brianstacks.multi_activityapp.EnteredData;
import com.brianstacks.multi_activityapp.R;
import java.util.ArrayList;

/**
 * Created by Brian Stacks
 * on 12/5/14
 * for FullSail.edu.
 */
public class ListFrag extends ListFragment {
    public static final String TAG = "ListFrag.TAG";
    private static final String ARG_Name = "name";
    private static final String ARG_Age = "age";
    private static final String ARG_Race = "race";
    ArrayList<EnteredData> enteredDataArrayList;



    public static ListFrag newInstance( String name,String age,String race){
        ListFrag listFrag =  new ListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_Name, name);
        args.putString(ARG_Age,age);
        args.putString(ARG_Race,race);
        listFrag.setArguments(args);
        return  listFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView( LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        return _inflater.inflate(R.layout.display_fragment, _container, false);
    }


    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        Bundle args = getArguments();
        enteredDataArrayList = new ArrayList<>();
        if(args != null && args.containsKey(ARG_Name)&& args.containsKey(ARG_Age)&& args.containsKey(ARG_Race)) {
            Log.v("got arguments", args.getString(ARG_Name));
            EnteredData enteredData = new EnteredData();
            enteredData.setName(args.getString(ARG_Name));
            enteredData.setAge(args.getString(ARG_Age));
            enteredData.setRace(ARG_Race);
            enteredDataArrayList.add(enteredData);
            DataAdapter dataAdapter = new DataAdapter(getActivity(), enteredDataArrayList);
            setListAdapter(dataAdapter);

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String myString = (String) l.getItemAtPosition(position);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(myString);
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
