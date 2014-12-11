package com.brianstacks.multi_activityapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.brianstacks.multi_activityapp.Fragments.DetailFragment;
import com.brianstacks.multi_activityapp.Fragments.EnterDataFragment;

import java.util.ArrayList;


public class DetailViewActivity extends Activity implements DetailFragment.OnFragmentInteractionListener,EnterDataFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_view, menu);
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

    }

    @Override
    public void onFragmentInteraction2(String myText) {

    }
}
