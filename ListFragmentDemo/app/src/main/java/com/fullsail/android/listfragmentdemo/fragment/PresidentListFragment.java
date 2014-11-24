package com.fullsail.android.listfragmentdemo.fragment;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fullsail.android.listfragmentdemo.R;

public class PresidentListFragment extends ListFragment {
	
	public static final String TAG = "PresidentListFragment.TAG";
	
	public static PresidentListFragment newInstance() {
		PresidentListFragment frag = new PresidentListFragment();
		return frag;
	}
	
	@Override
	public void onActivityCreated(Bundle _savedInstanceState) {
		super.onActivityCreated(_savedInstanceState);
		
		String[] presidents = getResources().getStringArray(R.array.presidents);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, presidents);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView _l, View _v, int _position, long _id) {
		String president = (String)_l.getItemAtPosition(_position);
		
		new AlertDialog.Builder(getActivity())
		.setTitle(R.string.president)
		.setMessage(getString(R.string.selected, president))
		.setPositiveButton(R.string.ok, null)
		.show();
	}
}