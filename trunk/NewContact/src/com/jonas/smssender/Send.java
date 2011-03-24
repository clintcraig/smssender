package com.jonas.smssender;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.jonas.smssender.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Send extends Activity {

	private EditText inputPhoneNumber;
	private EditText inputMsg;
	private Button btnSelectMsg;
	private Button btnSelectContact;
	private Button btnSend;
	private Spinner tbSpinner;

	private static String OrgMsg = "";
	private static String OrgNumber = "";
	// define all variables for database
	private static final String DBNAME = "SMS";
	private static final int VERSION = 1;
	private String tableName = "";
	private static DBHelper dbhelper;
	// define a handler to handler thread message
	private Handler sqlCreHandeler;
	// define a SharedPreferences to save settings
	private SharedPreferences settings;
	private boolean sqlCreated;

	private HashMap<String, String> tbNameMap;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 取得回传的intent,判断是否包含需要的信息
		Intent intent = this.getIntent();
		if (intent.hasExtra("Message")) {

			String newMsg = "";
			Bundle bundle = this.getIntent().getExtras();
			newMsg = bundle.getString("Message");
			inputMsg.setText(newMsg);
			OrgMsg = newMsg;
			// Log.i("OrgMsg", OrgMsg);
		}

		if (intent.hasExtra("Number")) {
			Bundle bundle = this.getIntent().getExtras();
			String newNumber = "";

			ArrayList<String> number = bundle.getStringArrayList("Number");
			for (int i = 0; i < number.size(); i++) {
				newNumber += number.get(i).replace("-", "") + ";";
			}
			inputPhoneNumber.setText(newNumber);
			OrgNumber = newNumber;
			// Log.i("OrgMsg", OrgNumber);
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);

		// get controls
		btnSelectMsg = (Button) this.findViewById(R.id.btnSelectMsg);
		btnSelectContact = (Button) this.findViewById(R.id.btnSelectContact);
		btnSend = (Button) this.findViewById(R.id.btnSend);
		inputMsg = (EditText) this.findViewById(R.id.InputMessage);
		inputPhoneNumber = (EditText) this.findViewById(R.id.inputPhoneNumber);

		ArrayList<String> test = new ArrayList<String>();
		test.add("春节");
		test.add("情人节");
		test.add("国庆节");

		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, test);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		tbSpinner = (Spinner) this.findViewById(R.id.dbSpinner);
		tbSpinner.setAdapter(spinnerAdapter);
		tbSpinner.setPrompt("请选择数据库");
		tbSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				tableName = arg0.getItemAtPosition(arg2).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		// 初始化Spinner值
		tbNameMap = new HashMap<String, String>();
		tbNameMap.put("春节", "Spring");
		tbNameMap.put("情人节", "Valentine");
		tbNameMap.put("国庆节", "Nation");

		// Log.i("OrgMsg", OrgMsg);
		// Log.i("OrgNumber", OrgNumber);
		inputMsg.setText(OrgMsg);
		inputPhoneNumber.setText(OrgNumber);

		settings = getPreferences(MODE_PRIVATE);

		// 取得回传的intent,判断是否包含需要的信息
		/*Intent intent = this.getIntent();
		if (intent.hasExtra("Message")) {

			String newMsg = "";
			Bundle bundle = this.getIntent().getExtras();
			newMsg = bundle.getString("Message");
			inputMsg.setText(newMsg);
			OrgMsg = newMsg;
			// Log.i("OrgMsg", OrgMsg);
		}

		if (intent.hasExtra("Number")) {
			Bundle bundle = this.getIntent().getExtras();
			String newNumber = "";

			ArrayList<String> number = bundle.getStringArrayList("Number");
			for (int i = 0; i < number.size(); i++) {
				newNumber += number.get(i).replace("-", "") + ";";
			}
			inputPhoneNumber.setText(newNumber);
			OrgNumber = newNumber;
			// Log.i("OrgMsg", OrgNumber);
		}*/
		// handeler messages send from sqlThread
		sqlCreHandeler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				// Log.i("Income Messages", msg.toString());

				Toast.makeText(getApplicationContext(),
						"Database has been Created", Toast.LENGTH_SHORT).show();

				dbhelper.close();
				// create a preference "dbCreated" to save database create state
				SharedPreferences.Editor editor = settings.edit();
				editor.putBoolean("dbCreated", true);
				editor.commit();
			}

		};

		sqlCreated = settings.getBoolean("dbCreated", false);
		// Log.i("sqlCreate", Boolean.toString(sqlCreated));
		if (sqlCreated == false) {
			sqlThread sqlThread = new sqlThread();
			sqlThread.start();
		}

		btnSelectContact.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Send.this, Contact.class);
				Send.this.finish();
				startActivity(intent);
				
			}

		});

		btnSelectMsg.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("tbName", tbNameMap.get(tableName).toString());
				intent.putExtras(bundle);
				intent.setClass(Send.this, Sms.class);
				Send.this.finish();
				startActivity(intent);
			}
		});
		// 短信发送部分
		btnSend.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				String smsNumber = inputPhoneNumber.getText().toString();
				String smsContent = inputMsg.getText().toString();
				if (smsNumber.length() == 0 || smsContent.length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Please check phone number and message",
							Toast.LENGTH_SHORT).show();
				} else {
					String[] smsNumberGroup = smsNumber.split(";");
					SmsManager smsManager = SmsManager.getDefault();
					PendingIntent pintent = PendingIntent.getBroadcast(
							Send.this, 0, new Intent("SMS_SENT"), 0);
					SMSReceiver smsreceiver = new SMSReceiver();
					IntentFilter receiverFilter = new IntentFilter("SMS_SENT");
					registerReceiver(smsreceiver, receiverFilter);
					for (int i = 0; i < smsNumberGroup.length; i++) {

						smsManager.sendTextMessage(smsNumberGroup[i], null,
								smsContent, pintent, null);
						Toast.makeText(
								getApplicationContext(),
								"Number is :" + smsNumberGroup[i].toString()
										+ "SMS Content is :"
										+ inputMsg.getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
				}

			}

		});
	}

	public class SMSReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {

			int resultCode = getResultCode();

			switch (resultCode) {
			case Activity.RESULT_OK:
				Toast.makeText(getApplicationContext(), "sms sent",
						Toast.LENGTH_SHORT).show();
				break;
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:

				break;
			case SmsManager.RESULT_ERROR_NO_SERVICE:

				break;
			case SmsManager.RESULT_ERROR_NULL_PDU:

				break;
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				break;
			}
		}
	}

	// 监听BACK按钮，显示退出对话框
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Builder exitDialog = new AlertDialog.Builder(Send.this);
			exitDialog.setTitle("确定退出");
			exitDialog.setMessage("真的要退出吗？");
			exitDialog.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							// android.os.Process.killProcess(android.os.Process
							// .myPid());
                            Send.this.finish();
						}

					});
			exitDialog.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			exitDialog.show();

			break;
		}
		return super.onKeyDown(keyCode, event);

	}

	// override run() method to implement init database and send Msg to main
	// Thread
	class sqlThread extends Thread {

		@Override
		public void run() {
			dbhelper = new DBHelper(getApplicationContext(), DBNAME, null,
					VERSION);
			SQLiteDatabase db = dbhelper.getWritableDatabase();
			Message toMain = sqlCreHandeler.obtainMessage();

			toMain.obj = "Database has been created";

			sqlCreHandeler.sendMessage(toMain);
		}

	}

}
