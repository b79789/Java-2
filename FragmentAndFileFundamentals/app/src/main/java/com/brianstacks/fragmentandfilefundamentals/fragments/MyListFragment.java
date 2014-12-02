package com.brianstacks.fragmentandfilefundamentals.fragments;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.brianstacks.fragmentandfilefundamentals.MainActivity;
import com.brianstacks.fragmentandfilefundamentals.R;

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

        public void displayText(String myText);
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
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);
        return view;
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
                            String myString  = parent.getItemAtPosition(position).toString();
                            Log.v("My String:",myString);
                            // replace the spaces with + to encode into the url
                            Log.v("EditText String here:", myString);
                            String encodedString = myString.replace(" ", "+");
                            //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
                            if (isOnline()) {
                                requestData("http://api.sportsdatallc.org/nfl-t1/2014/REG/" + encodedString + "/schedule.json?api_key=ytdtx2yuu95p83g3yu2v4cvu");
                            } else
                                Toast.makeText(getActivity(), "Network isn't available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        String[] teams = getResources().getStringArray(R.array.teamList);
        GamesAdapter adapter2 = new GamesAdapter(this.getActivity(),R.layout.item_place,gameList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, teams);
        setListAdapter(adapter2);
    }
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        String team = (String)_l.getItemAtPosition(_position);
        mListener.displayText(team);

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
            if (result == null){
                Toast.makeText(getActivity(), "Can't connect to API", Toast.LENGTH_SHORT).show();
                return;
            }
            gameList = (ArrayList<Games>) JSONParser.parseFeed(result);
            updateDisplay(gameList);
            Log.v("GameList", gameList.get(0).toString());


        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }


    public void updateDisplay(ArrayList<Games>gameList){

        // get instance of the Master List fragment then replaces container1 and commits it to the activity

        MyListFragment frag = MyListFragment.newInstance(gameList);

        getFragmentManager().beginTransaction()

                .replace(R.id.fragment_container, frag, MyListFragment.TAG).commit();

    }

}
