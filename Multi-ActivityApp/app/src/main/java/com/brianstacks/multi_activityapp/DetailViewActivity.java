package com.brianstacks.multi_activityapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.brianstacks.multi_activityapp.Fragments.DetailFragment;
import com.brianstacks.multi_activityapp.Fragments.EnterDataFragment;
import com.brianstacks.multi_activityapp.Fragments.ListFrag;

import java.util.ArrayList;


public class DetailViewActivity extends Activity implements DetailFragment.OnFragmentInteractionListener,EnterDataFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String race = intent.getStringExtra("race");
        Log.v("myRace",race);
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        DetailFragment detailFragment =  DetailFragment.newInstance(name, age, race);
        trans.replace(R.id.fragment_container2, detailFragment, DetailFragment.TAG);
        trans.commit();
    }


    @Override
    public void onFragmentInteraction2(EnteredData enteredData) {
        DetailFragment dFrag = (DetailFragment) getFragmentManager().findFragmentByTag(DetailFragment.TAG);
        if (dFrag == null){

            dFrag = DetailFragment.newInstance(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, dFrag, DetailFragment.TAG)
                    .commit();
        }else {
            dFrag.setDisplayInfo(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
        }
    }



    @Override
    public void onFragmentInteraction(EnteredData enteredData) {
        DetailFragment dFrag = (DetailFragment) getFragmentManager().findFragmentByTag(DetailFragment.TAG);
        if (dFrag == null){

            dFrag = DetailFragment.newInstance(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, dFrag, DetailFragment.TAG)
                    .commit();
        }else {
            dFrag.setDisplayInfo(enteredData.getName(),enteredData.getAge(),enteredData.getRace());
        }
    }


    public void onShare(View v){

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My name is"+" "+name );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share data with..."));
    }

    public void onDelete(View v){
        final ListView listView = (ListView) findViewById(android.R.id.list);

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Get our TextView and set some text to it.
                        TextView t1 = (TextView) findViewById(R.id.text1);
                        TextView t2 = (TextView) findViewById(R.id.text2);
                        TextView t3 = (TextView) findViewById(R.id.text3);
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
