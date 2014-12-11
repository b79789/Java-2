/*
* Brian Stacks
* Java 2
* 11-24-2014
* */
package com.brianstacks.fragmentandfilefundamentals;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.brianstacks.fragmentandfilefundamentals.fragments.DetailFragment;
import com.brianstacks.fragmentandfilefundamentals.fragments.MyListFragment;
import java.util.ArrayList;


public class MainActivity extends Activity implements MyListFragment.OnListItemClickListener {

    ArrayList<Games> gamesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        MyListFragment frag = MyListFragment.newInstance(gamesArrayList);
        trans.replace(R.id.fragment_container, frag, MyListFragment.TAG);
        DetailFragment dFrag = DetailFragment.newInstance("","","");
        trans.replace(R.id.fragment_container2,dFrag,DetailFragment.TAG);
        trans.commit();
   }



    @Override
    public void displayText(Games games) {
        DetailFragment dFrag = (DetailFragment) getFragmentManager().findFragmentByTag(DetailFragment.TAG);
        if (dFrag == null){

            dFrag = DetailFragment.newInstance(games.getHome(),games.getAway(),games.getVenue());
            getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container2, dFrag, DetailFragment.TAG)
                .commit();
        }else {
            dFrag.setDisplayInfo(games.getHome(),games.getAway(),games.getVenue());
        }
    }



}
