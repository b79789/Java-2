package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.brianstacks.fragmentandfilefundamentals.Games;
import com.brianstacks.fragmentandfilefundamentals.GamesAdapter;
import com.brianstacks.fragmentandfilefundamentals.HttpManager;
import com.brianstacks.fragmentandfilefundamentals.JSONParser;
import com.brianstacks.fragmentandfilefundamentals.R;
import com.brianstacks.fragmentandfilefundamentals.helperclass.Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Brian Stacks
 * on 11/24/14
 * for FullSail.edu.
 */
public class MyListFragment extends ListFragment {
    ArrayList<Games>  gameList;
    Spinner mySpin;
    public static final String GameList_Text = "DetailFragment.Arg_Text";
    public static final String TAG = "MyListFragment.TAG";
    private OnListItemClickListener mListener;
    public static MyListFragment newInstance(ArrayList<Games> gameList) {
        MyListFragment frag = new MyListFragment();
        Bundle args= new Bundle();
        args.putSerializable(GameList_Text,gameList);
        frag.setArguments(args);
        return frag;
    }

    public interface OnListItemClickListener{

        public void displayText(Games games);
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
        return _inflater.inflate(R.layout.display_fragment, _container, false);
    }

    @Override
    public void onActivityCreated(final Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

       final Button mybutton = (Button) getView().findViewById(R.id.addButton);
        mySpin = (Spinner) getView().findViewById(R.id.myEditText);
        String[] spinnerNumbers = getResources().getStringArray(R.array.weeksList);
        if (mySpin != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spinnerNumbers);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpin.setAdapter(arrayAdapter);
            mySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(final AdapterView<?> parent, View view,final int position, long id) {
                    mybutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Helper helper = new Helper(getActivity());
                            helper.getNetInfo();
                            Log.v("Online value:", String.valueOf(helper.getNetInfo()));
                            String myString  = parent.getItemAtPosition(position).toString();
                            // replace the spaces with + to encode into the url
                            String encodedString = myString.replace(" ", "+");
                            //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
                            if (helper.getNetInfo()) {
                                requestData("http://api.sportsdatallc.org/nfl-t1/2014/REG/" + encodedString + "/schedule.json?api_key=ytdtx2yuu95p83g3yu2v4cvu");
                            } else
                                requestData("")
;                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Games game = (Games)_l.getItemAtPosition(_position);
        mListener.displayText(game);

    }

    // method to get the data from ASYNC task
    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    // method to check internet connectivity
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    // Async task method to do network action in
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Helper helper = new Helper(getActivity());
            if (result == null){
                helper.readFromFile(getActivity(),"result.dat");
                gameList = (ArrayList<Games>) JSONParser.parseFeed(helper.readFromFile(getActivity(),"result.dat"));
                final GamesAdapter cArrayAdapter = new GamesAdapter(getActivity(),gameList);
                setListAdapter(cArrayAdapter);
                Toast.makeText(getActivity(), "Can't connect to API getting local data.", Toast.LENGTH_SHORT).show();
                return;
            }
            helper.writeToFile(getActivity(),"result.dat",result);
            gameList = (ArrayList<Games>) JSONParser.parseFeed(result);
            final GamesAdapter cArrayAdapter = new GamesAdapter(getActivity(),gameList);
            setListAdapter(cArrayAdapter);
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }





}
