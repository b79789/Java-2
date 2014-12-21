package com.brianstacks.multi_activityapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brianstacks.multi_activityapp.EnteredData;
import com.brianstacks.multi_activityapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFrag.TAG";
    private static final String ARG_Name = "name";
    private static final String ARG_Age = "age";
    private static final String ARG_Race = "race";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mAge;
    private String mRace;
    private OnFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String name, String age, String race) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Name, name);
        args.putString(ARG_Age, age);
        args.putString(ARG_Race, race);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_Name);
            mAge = getArguments().getString(ARG_Age);
            mRace= getArguments().getString(ARG_Race);
        }
    }

    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        if (getArguments() != null) {

            setDisplayInfo(getArguments().getString(ARG_Name), getArguments().getString(ARG_Age), getArguments().getString(ARG_Race));


        }else {
            setDisplayInfo("No","data","set");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction2(EnteredData enteredData);
    }

    public void setDisplayInfo(String name, String age, String race) {



        // Get our TextView and set some text to it.
        TextView t1 = (TextView) getActivity().findViewById(R.id.text1);
        TextView t2 = (TextView) getActivity().findViewById(R.id.text2);
        TextView t3 = (TextView) getActivity().findViewById(R.id.text3);
        t1.setText(name);
        t2.setText(age);
        t3.setText(race);
    }

    public void onShare(View view){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My name is"+mName);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share data with..."));
    }
}
