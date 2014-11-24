package com.fullsail.android.listfragmentdemo;

import android.app.Activity;
import android.os.Bundle;

import com.fullsail.android.listfragmentdemo.fragment.PresidentListFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(_savedInstanceState == null) {
			PresidentListFragment frag = PresidentListFragment.newInstance();
			getFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, frag, PresidentListFragment.TAG).commit();
		}
	}
}
