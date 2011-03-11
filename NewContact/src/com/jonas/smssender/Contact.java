package com.jonas.smssender;

import java.util.ArrayList;
import com.jonas.smssender.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Contact extends Activity {

	private ArrayList<String> contactName;
	private ArrayList<String> phoneNum;
	private ListView listContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		listContact = (ListView) this.findViewById(R.id.listContact);

		contactName = new ArrayList<String>();
		phoneNum = new ArrayList<String>();

		Cursor cur = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.TYPE + "="
						+ ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
				null, null);
		// if(cur !=null && cur.getCount()>=0)
		// {
		// ListAdapter adapter = new SimpleCursorAdapter(this,
		// android.R.layout.simple_list_item_2, cur,
		// new String[]
		// {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER},
		// new int[] {android.R.id.text1,android.R.id.text2});
		// listContact.setAdapter(adapter);
		// }
		if (cur != null && cur.getCount() >= 0) {
			ListAdapter adapter = new SimpleCursorAdapter(
					this,
					R.layout.listsytle,
					cur,
					new String[] {
							ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
							ContactsContract.CommonDataKinds.Phone.NUMBER },
					new int[] { R.id.topTextView, R.id.bottomTextView });
			listContact.setAdapter(adapter);
			listContact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			listContact.setFocusable(true);
			listContact.setItemsCanFocus(true);
		}

		// remove "-" from Phonenumber
		listContact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// Bundle selectedContact = new Bundle();
				// String number=((TwoLineListItem)
				// arg1).getText2().getText().toString().replace("-", "");
				// Log.i("Number", number);
				// selectedContact.putString("Number", number);
				//               
				// Intent intent =new Intent();
				// intent.putExtras(selectedContact);
				// intent.setClass(Contact.this, Send.class);
				// startActivity(intent);
				// Contact.this.finish();

				RelativeLayout rl = (RelativeLayout) arg1;
				TextView tvName = (TextView) rl.getChildAt(1);
				String name = tvName.getText().toString();
				Log.i("Name", name);

				TextView tvNumber = (TextView) rl.getChildAt(2);
				String number = tvNumber.getText().toString();

				listContact.setSelection(arg2);
				CheckBox cb = (CheckBox) rl.getChildAt(0);
				cb.setChecked(true);

				Log.i("Number", number);
				contactName.add(name);
				phoneNum.add(number);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 0, 0, R.string.OK);
		menu.add(0, 1, 1, R.string.Cancel);
		// return super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// return super.onOptionsItemSelected(item);
		int item_id = item.getItemId();
		Bundle selectedContact = new Bundle();
		Intent intent = new Intent();
		switch (item_id) {
		case 0:
			// for(int i=0;i<=phoneNum.size()-1;i++)
			// {
			// String orgContactInfo = phoneNum.get(i).toString();
			// Toast.makeText(this, orgContactInfo, Toast.LENGTH_SHORT).show();
			// }
			//                      
			selectedContact.putStringArrayList("Number", phoneNum);
			intent.putExtras(selectedContact);
			intent.setClass(Contact.this, Send.class);
			startActivity(intent);
			Contact.this.finish();

			break;
		case 1:
			intent.setClass(Contact.this, Send.class);
			startActivity(intent);
			Contact.this.finish();
			break;
		}
		return true;
	}

}
