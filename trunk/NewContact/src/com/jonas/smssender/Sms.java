package com.jonas.smssender;

import com.jonas.smssender.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Sms extends Activity {
	/** Called when the activity is first created. */
	private ListView list;
	private static final String DBNAME = "SMS";
	private static final int VERSION = 1;
	private String tbName = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);
		// create new DBhelper instance
		DBHelper dbhelper = new DBHelper(this, DBNAME, null, VERSION);
		SQLiteDatabase db = dbhelper.getWritableDatabase();

		// get tbName from Send Activity
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		tbName = bundle.getString("tbName");

		list = (ListView) this.findViewById(R.id.ListView01);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setFocusable(false);
		list.setOnItemClickListener(new OnItemClickListener() {

			// get the message in selected ListView
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Bundle selectedMsg = new Bundle();
				selectedMsg.putString("Message", ((TextView) arg1).getText()
						.toString());

				Intent intent = new Intent();
				intent.putExtras(selectedMsg);
				intent.setClass(Sms.this, Send.class);
				startActivity(intent);
				Sms.this.finish();

			}
		});

		Cursor cur = db.query(tbName, new String[] { DBHelper.ID,
				DBHelper.MESSAGE }, null, null, null, null, null);

		if (cur != null && cur.getCount() >= 0) {
			ListAdapter adapter = new SimpleCursorAdapter(this,
					android.R.layout.simple_list_item_1, cur,
					new String[] { DBHelper.MESSAGE },
					new int[] { android.R.id.text1 });
			list.setAdapter(adapter);
		}
		dbhelper.close();

	}
	//Back Button handler
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
									Intent intent = new Intent();
									intent.setClass(Sms.this, Send.class);
									Sms.this.finish();
									startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}

}
