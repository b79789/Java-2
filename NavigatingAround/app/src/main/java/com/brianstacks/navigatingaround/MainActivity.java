package com.brianstacks.navigatingaround;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.brianstacks.navigatingaround.Fragments.AddFragment;
import com.brianstacks.navigatingaround.Fragments.ListFragment;


public class MainActivity extends ActionBarActivity implements AddFragment.OnFragmentInteractionListener,ListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        switch (item.getItemId()){
            case R.id.action_settings:
                Log.v("Settings:","Has been activated");
                return true;
            case R.id.action_add:
                addAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void addAction(){
        Log.v("Add:","Has been activated");

        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        AddFragment frag = AddFragment.newInstance("","");
        trans.replace(R.id.fragment_container, frag, AddFragment.TAG).commit();
    }

    @Override
    public void onFragmentInteraction(String fName,String lName) {

    }

    @Override
    public void onFragmentInteraction2(String name1, String name2) {

    }
}
