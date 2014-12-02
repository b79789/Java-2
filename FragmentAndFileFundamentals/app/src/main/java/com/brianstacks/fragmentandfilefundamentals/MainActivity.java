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
import android.view.Menu;
import android.view.MenuItem;
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



}
