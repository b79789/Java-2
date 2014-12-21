package com.brianstacks.navigatingaround.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brianstacks.navigatingaround.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    public static final String TAG = "ListFragment.TAG";
    private static final String ARG_PARAM1 = "name1";
    private static final String ARG_PARAM2 = "name2";

    // TODO: Rename and change types of parameters
    private String mName1;
    private String mName2;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String name1, String name2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name1);
        args.putString(ARG_PARAM2, name2);
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName1 = getArguments().getString(ARG_PARAM1);
            mName2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);
        final ArrayList<String> arrayList = new ArrayList<>();
        final ListView lv = (ListView)getView().findViewById(R.id.myList);
        if (getArguments() != null) {
            mName1 = getArguments().getString(ARG_PARAM1);
            mName2 = getArguments().getString(ARG_PARAM2);
            Log.i("name1",mName1);
            Log.i("name2",mName2);
            arrayList.add(mName1);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    arrayList );

            lv.setAdapter(arrayAdapter);
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ArrayAdapter <String> adapter=new ArrayAdapter<String>
                            (getActivity(),android.R.layout.simple_list_item_1,arrayList);

                    arrayList.remove(position);

                    lv.setAdapter(adapter);
                    return true ;
                }
            });
        }else {
            arrayList.add("Need to add data");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    arrayList );
            lv.setAdapter(arrayAdapter);
        }

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction2(String name1,String name2);
    }

}
