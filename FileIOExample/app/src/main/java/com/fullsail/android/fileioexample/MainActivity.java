package com.fullsail.android.fileioexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final String FILENAME = "test.txt";

	EditText mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEdit = (EditText)findViewById(R.id.edit);
		
		findViewById(R.id.load).setOnClickListener(this);
		findViewById(R.id.save).setOnClickListener(this);
	}
	
	private void writeToFile(String _filename, String _data) {
		File external = getExternalFilesDir(null);
		File file = new File(external, _filename);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(_data.getBytes());
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
private String readFromFile(String _filename) {
	File external = getExternalFilesDir(null);
	File file = new File(external, _filename);
	if(!file.exists()) {
		return null;
	}
	
	try {
		FileInputStream fin = new FileInputStream(file);
		InputStreamReader inReader = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(inReader);
		
		StringBuffer buffer = new StringBuffer();
		String text = null;
		while((text = reader.readLine()) != null) {
			buffer.append(text + "\n");
		}
		
		reader.close();
		return buffer.toString().trim();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	return null;
}
	
	@Override
	public void onClick(View _v) {
		if(_v.getId() == R.id.save) {
			writeToFile(FILENAME, mEdit.getText().toString());
		} else {
			String text = readFromFile(FILENAME);
			if(text != null) {
				mEdit.setText(text);
			}
		}
	}
}
