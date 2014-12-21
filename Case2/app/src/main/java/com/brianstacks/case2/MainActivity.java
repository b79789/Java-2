package com.brianstacks.case2;

import java.util.ArrayList;
import java.util.Locale;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener,CurrentForecastFragment.OnFragmentInteractionListener,HourlyForecastFragment.OnFragmentInteractionListener,WeeklyForecastFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        Helper helper = new Helper(getApplicationContext());

        @Override
        public Fragment getItem(int position) {
            if (position == 1){
                Log.v("Position","1");

                //check to see if online and if so continue to get the JSON data if not toast a message telling the user no connection
                if (helper.getNetInfo()) {
                    requestData("http://api.wunderground.com/api/44b0c511a3495cee/conditions/q/CA/San_Francisco.json");
                } else
                    requestData("")
                            ;

            }else if (position ==2){
                Log.v("Position","2");

                if (helper.getNetInfo()) {
                    requestData("http://api.wunderground.com/api/44b0c511a3495cee/hourly/q/CA/San_Francisco.json");
                } else
                    requestData("")
                            ;
            }else if (position ==3){
                Log.v("Position","3");

                if (helper.getNetInfo()) {
                    requestData("http://api.wunderground.com/api/44b0c511a3495cee/forecast7day/q/CA/San_Francisco.json");
                } else
                    requestData("")
                            ;
            }

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
                case 2:
                    return getString(R.string.title_section3);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            TextView textView2 = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView2.setGravity(Gravity.CENTER);

            if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).equals("1")){
                textView.setText("Current Forecast\r\n 69");
            }else if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).equals("2")){
                textView.setText("Hourly Forecast \r\n Now: 64, 1 Hour: 61, 2 Hours: 57");
            }else if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).equals("3")){
                textView.setText("Weekly Forecast \r\n Mon: High 70 Low 51, Tues: High 65 Low 49,\r\n Wend: High 64 Low 47, Thurs: High 63 Low 44,\r\n Fri: High 73 Low 55, Sat: High 72 Low 54, Sun: High 69 Low 51");
            }
            return textView;
        }
    }



    // method to get the data from ASYNC task
    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    // Async task method to do network action in
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<String> gameList;
            Helper helper = new Helper(getApplicationContext());
            if (result == null) {
                helper.readFromFile(getApplicationContext(), "result.dat");
                gameList = (ArrayList<String>) JSONParser.parseFeed(helper.readFromFile(getApplicationContext(), "result.dat"));
                final ArrayAdapter cArrayAdapter = new ArrayAdapter(getApplicationContext(), gameList.indexOf(0));
                //setListAdapter(cArrayAdapter);
                Toast.makeText(getApplicationContext(), "Can't connect to API getting local data.", Toast.LENGTH_SHORT).show();
                return;
            }
            helper.writeToFile(getApplication(), "result.dat", result);
            gameList = (ArrayList<String>) JSONParser.parseFeed(result);
            Log.v("gameList2:",result);
           // final ArrayAdapter cArrayAdapter = new ArrayAdapter(getApplicationContext(), gameList.indexOf(0));
            //setListAdapter(cArrayAdapter);
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }

}
