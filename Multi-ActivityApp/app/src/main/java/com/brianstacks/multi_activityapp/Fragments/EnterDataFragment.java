package com.brianstacks.multi_activityapp.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    public static final String enteredDataList_Text = "EnterDataFragment.Arg_Text";
    private static final String ARG_Name = "name";
    private static final String ARG_Age = "age";
    private static final String ARG_Race = "race";
    private String mName;
    private String mAge;
    private String mRace;
    private OnFragmentInteractionListener mListener;
    ArrayList<EnteredData> enteredDataArrayList = new ArrayList<>();


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

                EditText e1 = (EditText)getActivity().findViewById(R.id.e1);
                EditText e2 = (EditText)getActivity().findViewById(R.id.e2);
                EditText e3 = (EditText)getActivity().findViewById(R.id.e3);
                EnteredData enteredData = new EnteredData();
                enteredData.setName(e1.getText().toString());
                enteredData.setAge(e2.getText().toString());
                enteredData.setRace(e3.getText().toString());
                enteredDataArrayList.add(enteredData);
                mListener.onFragmentInteraction(enteredDataArrayList);

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
        public void onFragmentInteraction(ArrayList<EnteredData> enteredDataArrayList1);

    }

}
