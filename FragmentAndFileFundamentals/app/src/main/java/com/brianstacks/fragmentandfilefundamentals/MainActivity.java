/*
* Brian Stacks
* Java 2
* 11-24-2014
* */
package com.brianstacks.fragmentandfilefundamentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.brianstacks.fragmentandfilefundamentals.fragments.DetailFragment;
import com.brianstacks.fragmentandfilefundamentals.fragments.MyListFragment;

import java.util.List;


public class MainActivity extends Activity implements MyListFragment.OnListItemClickListener {

    List<MyTask> tasks;
    List<Games>  gamesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();

        MyListFragment frag = MyListFragment.newInstance();
        trans.replace(R.id.fragment_container, frag, MyListFragment.TAG);
        DetailFragment dFrag = DetailFragment.newInstance();
        trans.replace(R.id.fragment_container2, dFrag, DetailFragment.TAG);
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

    // Async task method to do network action in
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // add this to the task
            tasks.add(this);

        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            tasks.remove(this);
            if (result == null){
                Toast.makeText(MainActivity.this,"Can't connect to API",Toast.LENGTH_SHORT).show();
                return;
            }
            gamesList = JSONParser.parseFeed(result);

        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
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

    public void onClick(View v) {

        EditText myEdit = (EditText)findViewById(R.id.myEditText);
        // create a string to grab the text of the edit text
        String myString = myEdit.getText().toString();
        // replace the spaces with + to encode into the url
        Log.v("EditText String here:",myString);
        String encodedString = myString.replace(" ", "+");
        //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
        if (isOnline()) {
            requestData("http://api.sportsdatallc.org/nfl-t1/2014/REG/"+encodedString+"/schedule.json?api_key=ytdtx2yuu95p83g3yu2v4cvu");
        } else Toast.makeText(this, "Network isn't available", Toast.LENGTH_SHORT).show();


    }

}
