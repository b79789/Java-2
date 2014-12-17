package com.brianstacks.multi_activityapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.brianstacks.multi_activityapp.EnteredData;
import com.brianstacks.multi_activityapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnterDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EnterDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterDataFragment extends Fragment {

    public static final String TAG = "EnterDataFragment.TAG";
    private OnFragmentInteractionListener mListener;



    public static EnterDataFragment newInstance() {
        return new EnterDataFragment();
    }

    public EnterDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_data, container, false);
    }


    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        Button addButton;
        addButton = (Button)getActivity().findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnteredData enteredData = new EnteredData();
                EditText e1 = (EditText)getActivity().findViewById(R.id.e1);
                EditText e2 = (EditText)getActivity().findViewById(R.id.e2);
                EditText e3 = (EditText)getActivity().findViewById(R.id.e3);
                if (e1.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Must enter name");
                    builder1.setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else if (e2.getText().toString().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Must enter age");
                    builder1.setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else if (e3.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Must enter race");
                    builder1.setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else {
                    enteredData.setName(e1.getText().toString());
                    enteredData.setAge(e2.getText().toString());
                    enteredData.setRace(e3.getText().toString());
                    

                    mListener.onFragmentInteraction(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
                }
            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction( String name,String age,String race);

    }

}
