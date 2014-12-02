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
    public static final String Arg_Text = "DetailFragment.Arg_Text";

    public static DetailFragment newInstance(String myText){
        DetailFragment dFrag = new DetailFragment();
        Bundle args= new Bundle();
        args.putString(Arg_Text,myText);
        dFrag.setArguments(args);
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

        Bundle args =getArguments();
        if (args != null && args.containsKey(Arg_Text)){
            setDisplayInfo(args.getString(Arg_Text));
        }


    }

    public void setDisplayInfo(String myText){
       // getArguments().putString(ARG_TAG,myText);
        // Get our TextView and set some text to it.

        TextView t1 =(TextView) getActivity().findViewById(R.id.text1);
        TextView t2 =(TextView) getActivity().findViewById(R.id.text2);
        TextView t3 =(TextView) getActivity().findViewById(R.id.text3);
        t1.setText(myText);
    }
}
