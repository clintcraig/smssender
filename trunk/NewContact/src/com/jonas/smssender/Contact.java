package com.jonas.smssender;

import java.util.ArrayList;
import java.util.HashMap;

import com.jonas.smssender.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Contact extends Activity {

	private ArrayList<HashMap<String, Object>> listItem;
	private ListView listContact;
	private ArrayList<String> PhoneNum;
	private int positon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		listContact = (ListView) this.findViewById(R.id.listContact);

		listItem = new ArrayList<HashMap<String, Object>>();
		PhoneNum = new ArrayList<String>();
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
		while (cur.moveToNext()) {
			HashMap<String, Object> readMap = new HashMap<String, Object>();
			readMap
					.put(
							"dispName",
							cur
									.getString(cur
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
			// cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			readMap
					.put(
							"PhoneNum",
							cur
									.getString(cur
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
			listItem.add(readMap);

		}
		cur.close();
		final MyAdapter adapter = new MyAdapter(this, listItem);
		listContact.setAdapter(adapter);
		listContact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listContact.setFocusable(true);
		listContact.setItemsCanFocus(true);

		// remove "-" from Phonenumber
		listContact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				CheckBox cb = (CheckBox) arg0.getChildAt(0).findViewById(
						R.id.cbListItem);
				// cb.toggle();
				/* 记录选中状态 */

				if (adapter.checkstate.get(arg2)) {
					PhoneNum.remove(listItem.get(arg2).get("PhoneNum")
							.toString());
					cb.setChecked(false);
				} else {
					PhoneNum.add(listItem.get(arg2).get("PhoneNum").toString());
					cb.setChecked(true);
				}
				adapter.checkstate.put(arg2, cb.isChecked());
				positon = arg2;

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
			selectedContact.putStringArrayList("Number", PhoneNum);
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
    //Back Button Handler
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
									Intent intent = new Intent();
									intent.setClass(Contact.this, Send.class);
									Contact.this.finish();
									startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}

	public class MyAdapter extends BaseAdapter {

		private Context context;

		private ArrayList<HashMap<String, Object>> list;

		private LayoutInflater mInflater;

		private HashMap<Integer, Boolean> checkstate;

		public MyAdapter(Context ct, ArrayList<HashMap<String, Object>> lt) {

			context = ct;

			list = lt;

			mInflater = LayoutInflater.from(context);

			checkstate = new HashMap<Integer, Boolean>();
			for (int i = 0; i < lt.size(); i++) {
				checkstate.put(i, false);
			}

		}

		public final class ViewHolder {

			public TextView tvname;

			public TextView tvphone;

			public CheckBox checkbox;
		}

		@Override
		public int getCount() {

			// TODO Auto-generated method stub

			// 返回List的size

			if (list != null) {

				return list.size();

			} else {

				return 0;

			}

		}

		@Override
		public Object getItem(int position) {

			// TODO Auto-generated method stub

			if (list != null) {

				// 返回某个位置的Map<String,Object>

				return list.get(position);

			} else {

				return null;

			}
		}

		@Override
		public long getItemId(int position) {

			// TODO Auto-generated method stub

			// 返回当前位置

			return position;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// TODO Auto-generated method stub

			// 返回某个位置的View

			ViewHolder holder = null;

			if (convertView == null) {

				holder = new ViewHolder();

				convertView = mInflater.inflate(R.layout.listsytle, null);

				holder.tvname = (TextView) convertView
						.findViewById(R.id.topTextView);

				holder.tvphone = (TextView) convertView
						.findViewById(R.id.bottomTextView);

				holder.checkbox = (CheckBox) convertView
						.findViewById(R.id.cbListItem);
				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.tvname
					.setText(list.get(position).get("dispName").toString());

			holder.tvphone.setText(list.get(position).get("PhoneNum")
					.toString());

			holder.checkbox.setChecked(checkstate.get(position));

			return convertView;

		}
	}
}
