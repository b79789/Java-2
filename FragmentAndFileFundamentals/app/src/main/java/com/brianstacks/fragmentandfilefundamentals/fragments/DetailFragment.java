package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.brianstacks.fragmentandfilefundamentals.R;

/**
 * Created by Brian Stacks
 * on 11/24/14
 * for FullSail.edu.
 */
public class DetailFragment extends Fragment {
    public static final String TAG = "DetailFragment.TAG";

    public static DetailFragment newInstance(){
        DetailFragment dFrag = new DetailFragment();
        return  dFrag;
    }
    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.detail_view, _container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        TextView t1 =(TextView) getActivity().findViewById(R.id.text1);
        TextView t2 =(TextView) getActivity().findViewById(R.id.text2);
        TextView t3 =(TextView) getActivity().findViewById(R.id.text3);
        t1.setText("hello");


    }

    public void setDisplayInfo(String myText){
       // getArguments().putString(ARG_TAG,myText);
        // Get our TextView and set some text to it.
        TextView tv;
        tv = (TextView)getView().findViewById(R.id.text1);
        tv.setText(myText);
    }
}
