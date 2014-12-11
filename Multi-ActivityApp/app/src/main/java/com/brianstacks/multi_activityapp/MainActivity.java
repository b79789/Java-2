package com.brianstacks.multi_activityapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.brianstacks.multi_activityapp.Fragments.DetailFragment;
import com.brianstacks.multi_activityapp.Fragments.EnterDataFragment;
import com.brianstacks.multi_activityapp.Fragments.ListFrag;

import java.util.ArrayList;


public class MainActivity extends Activity implements DetailFragment.OnFragmentInteractionListener,EnterDataFragment.OnFragmentInteractionListener{

    private static final String ARG_Name = "name";
    private static final String ARG_Age = "age";
    private static final String ARG_Race = "race";
    private String mName;
    private String mAge;
    private String mRace;
    ArrayList<EnteredData> enteredDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        ListFrag listFrag =  ListFrag.newInstance(enteredDataArrayList);
        trans.replace(R.id.fragment_container, listFrag, ListFrag.TAG);
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
    public void onFragmentInteraction(ArrayList<EnteredData> enteredDataArrayList1) {
        Log.v("MyObject",enteredDataArrayList1.toString());
        ListFrag listFrag = (ListFrag)getFragmentManager().findFragmentByTag(ListFrag.TAG);
        if (listFrag == null){
            listFrag = ListFrag.newInstance(enteredDataArrayList1);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,listFrag,ListFrag.TAG)
                    .commit();

        }else {
            final  DataAdapter dataAdapter = new DataAdapter(this,enteredDataArrayList);
            listFrag.setListAdapter(dataAdapter);
        }
    }

    @Override
    public void onFragmentInteraction2(String myText) {

    }
}
