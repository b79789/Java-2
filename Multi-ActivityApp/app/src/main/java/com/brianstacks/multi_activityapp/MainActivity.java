package com.brianstacks.multi_activityapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.brianstacks.multi_activityapp.Fragments.DetailFragment;
import com.brianstacks.multi_activityapp.Fragments.EnterDataFragment;
import com.brianstacks.multi_activityapp.Fragments.ListFrag;
import java.util.ArrayList;


public class MainActivity extends Activity implements DetailFragment.OnFragmentInteractionListener,EnterDataFragment.OnFragmentInteractionListener{
    ArrayList<EnteredData> enteredDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredDataArrayList = new ArrayList<>();
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        ListFrag listFrag =  ListFrag.newInstance("Example","13","blue");
        trans.replace(R.id.fragment_container, listFrag, ListFrag.TAG);
        trans.commit();

    }

    public void addDetailsClick(View v) {
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        EnterDataFragment enterDataFragment = EnterDataFragment.newInstance();
        trans.replace(R.id.fragment_container, enterDataFragment, EnterDataFragment.TAG);
        trans.commit();
    }



    @Override
    public void onFragmentInteraction2(EnteredData enteredData) {


    }

    @Override
    public void onFragmentInteraction(EnteredData enteredData) {
        Helper helper = new Helper(this);
        Bundle bundle = new Bundle();
        ListFrag listFrag = (ListFrag) getFragmentManager().findFragmentByTag(ListFrag.TAG);
            helper.writeToFile(this, "name", enteredData.getName());
            helper.writeToFile(this, "age", enteredData.getAge());
            helper.writeToFile(this, "race", enteredData.getRace());
            enteredDataArrayList.add(enteredData);

            if (listFrag == null) {
                listFrag = ListFrag.newInstance(enteredData.getName(), enteredData.getAge(), enteredData.getRace());
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, listFrag, ListFrag.TAG)
                        .commit();

            } else {
                DataAdapter dataAdapter = new DataAdapter(this, enteredDataArrayList);
                listFrag.setListAdapter(dataAdapter);
            }
        }


}
