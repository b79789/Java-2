package com.brianstacks.multi_activityapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    public static final String enteredDataList_Text = "ListFragment.Arg_Text";
    ArrayList<EnteredData> enteredDataArrayList;
    private static final String ARG_Name = "name";
    private static final String ARG_Age = "age";
    private static final String ARG_Race = "race";
    private String mName;
    private String mAge;
    private String mRace;
    String[] textList = {"Please","Enter","Some","Data"};


    public static ListFrag newInstance(ArrayList<EnteredData> enteredDataArrayList1){
        ListFrag listFrag =  new ListFrag();
        Bundle args = new Bundle();
        args.putSerializable(enteredDataList_Text,enteredDataArrayList1);
        listFrag.setArguments(args);
        return new ListFrag();
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
        Button button = (Button) getActivity().findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mgr = getFragmentManager();
                FragmentTransaction trans = mgr.beginTransaction();
                EnterDataFragment enterDataFragment = EnterDataFragment.newInstance();
                trans.replace(R.id.fragment_container, enterDataFragment, EnterDataFragment.TAG);
                trans.commit();
            }
        });
        Bundle args = getArguments();
        if(args != null && args.containsKey(enteredDataList_Text))
        {
            Log.v("got arguments","got arguments");
            enteredDataArrayList = (ArrayList<EnteredData>) args.getSerializable(enteredDataList_Text);
            final  DataAdapter dataAdapter = new DataAdapter(getActivity(),enteredDataArrayList);
            setListAdapter(dataAdapter);
        }else {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
            builder1.setMessage("Bundle is empty");
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String text = " position:" + position + "  " + textList[position];
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(text);
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
