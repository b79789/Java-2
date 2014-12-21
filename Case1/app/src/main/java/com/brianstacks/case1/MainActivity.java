package com.brianstacks.case1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener,Team1Fragment.OnFragmentInteractionListener,Team2Fragment.OnFragmentInteractionListener,Team3Fragment.OnFragmentInteractionListener,Team4Fragment.OnFragmentInteractionListener,Team5Fragment.OnFragmentInteractionListener,Team6Fragment.OnFragmentInteractionListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[]{
                                getString(R.string.title_section1),
                                getString(R.string.title_section2),
                                getString(R.string.title_section3),
                                getString(R.string.title_section4),
                                getString(R.string.title_section5),
                                getString(R.string.title_section6),
                        }),
                this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
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
    public boolean onNavigationItemSelected(int position, long id) {
        if (position == 0){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team1Fragment enterDataFragment = Team1Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team1Fragment.TAG);
            trans.commit();
        }else if (position == 1){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team2Fragment enterDataFragment = Team2Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team2Fragment.TAG);
            trans.commit();

        }else if (position == 2){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team3Fragment enterDataFragment = Team3Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team3Fragment.TAG);
            trans.commit();
        }else if (position == 3){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team4Fragment enterDataFragment = Team4Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team4Fragment.TAG);
            trans.commit();
        }else if (position == 4){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team5Fragment enterDataFragment = Team5Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team5Fragment.TAG);
            trans.commit();
        }else if (position == 5){
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();
            Team6Fragment enterDataFragment = Team6Fragment.newInstance("","");
            trans.replace(R.id.container, enterDataFragment, Team6Fragment.TAG);
            trans.commit();
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
