package com.brianstacks.java2week3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.brianstacks.java2week3.Fragments.AddDetailsFragment;
import com.brianstacks.java2week3.Fragments.ListFragment;


public class MainActivity extends Activity implements ListFragment.OnFragmentInteractionListener,AddDetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        ListFragment listFrag =  ListFragment.newInstance("Example","13","blue");
        trans.replace(R.id.fragment_container, listFrag, ListFragment.TAG);
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

    @Override
    public void onFragmentInteraction(EnteredData enteredData) {

    }

    @Override
    public void onEnterData(final EnteredData enteredData) {

        Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("I","CLICKED IT!");
                FragmentManager mgr = getFragmentManager();
                FragmentTransaction trans = mgr.beginTransaction();
                ListFragment listFragment = ListFragment.newInstance(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
                trans.replace(R.id.fragment_container, listFragment, ListFragment.TAG).commit();

            }
        });
    }

    public void addDetailsClick(View v) {
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        AddDetailsFragment addDetailsFragment = AddDetailsFragment.newInstance();
        trans.replace(R.id.fragment_container, addDetailsFragment, AddDetailsFragment.TAG);
        trans.commit();
    }

}
