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

    public static DetailFragment newInstance(String homeTeam, String awayTeam, String venue) {
        DetailFragment dFrag = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("homeTeam", homeTeam);
        args.putString("awayTeam", awayTeam);
        args.putString("venue", venue);
        dFrag.setArguments(args);
        return dFrag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        return _inflater.inflate(R.layout.detail_view, _container, false);
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(Arg_Text)) {
            setDisplayInfo(args.getString("homeTeam"), args.getString("awayTeam"), args.getString("venue"));
        }


    }

    public void setDisplayInfo(String home, String away, String venue) {

        // Get our TextView and set some text to it.
        TextView t1 = (TextView) getActivity().findViewById(R.id.text1);
        TextView t2 = (TextView) getActivity().findViewById(R.id.text2);
        TextView t3 = (TextView) getActivity().findViewById(R.id.text3);
        t1.setText(home);
        t2.setText(away);
        t3.setText(venue);
    }
}
