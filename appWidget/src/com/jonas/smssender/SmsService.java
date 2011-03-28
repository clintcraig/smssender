package com.jonas.smssender;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		IntentFilter receiverFilter = new IntentFilter("SMS_SENT");
		registerReceiver(smsReciever, receiverFilter);
	}

	public BroadcastReceiver smsReciever = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
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
	};
}
