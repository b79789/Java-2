/*
* Brian Stacks
* Java 2
* 11-24-2014
* */
package com.brianstacks.fragmentandfilefundamentals;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.brianstacks.fragmentandfilefundamentals.fragments.DetailFragment;
import com.brianstacks.fragmentandfilefundamentals.fragments.MyListFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements MyListFragment.OnListItemClickListener {

    List<Games>  gameList;
    ArrayList<Games> gamesArrayList;
    Spinner mySpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        MyListFragment frag = MyListFragment.newInstance(gamesArrayList);
        trans.replace(R.id.fragment_container, frag, MyListFragment.TAG);
        trans.commit();
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // method to get the data from ASYNC task
    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    // method to check internet connectivity
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    @Override
    public void displayText(String myText) {
        DetailFragment dFrag = (DetailFragment) getFragmentManager().findFragmentByTag(DetailFragment.TAG);
        if (dFrag == null){
            dFrag = DetailFragment.newInstance(myText);
            getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container2, dFrag, DetailFragment.TAG)
                .commit();
        }else {
            dFrag.setDisplayInfo(myText);
        }
    }

    public void onClick(View v) {

        mySpin = (Spinner)findViewById(R.id.myEditText);
        mySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String myString  = parent.getItemAtPosition(position).toString();
                Log.v("My String:",myString);
                // replace the spaces with + to encode into the url
                Log.v("EditText String here:", myString);
                String encodedString = myString.replace(" ", "+");
                //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
                if (isOnline()) {
                    requestData("http://api.sportsdatallc.org/nfl-t1/2014/REG/" + encodedString + "/schedule.json?api_key=ytdtx2yuu95p83g3yu2v4cvu");
                } else
                    Toast.makeText(getParent(), "Network isn't available", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void updateDisplay(ArrayList<Games>gameList){

        // get instance of the Master List fragment then replaces container1 and commits it to the activity

        MyListFragment frag = MyListFragment.newInstance(gameList);

        getFragmentManager().beginTransaction()

                .replace(R.id.fragment_container, frag, MyListFragment.TAG).commit();

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
                Toast.makeText(MainActivity.this,"Can't connect to API",Toast.LENGTH_SHORT).show();
                return;
            }
            gameList = JSONParser.parseFeed(result);
            updateDisplay(gamesArrayList);
            Log.v("GameList", gameList.get(0).toString());


        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }


}
