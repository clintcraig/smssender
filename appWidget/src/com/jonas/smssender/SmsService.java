package com.jonas.smssender;

import android.app.Activity;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class SmsService extends Service {
    //some variable for update appWidget
	private static AppWidgetManager appWidgetMannager;
	private static RemoteViews remoteViews;
	private static ComponentName componentName;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		IntentFilter receiverFilter = new IntentFilter("SMS");
		//register smsReciever
		registerReceiver(smsReciever, receiverFilter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//unregister smsReciever
		unregisterReceiver(smsReciever);
	}

	public BroadcastReceiver smsReciever = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			int resultCode = getResultCode();
			String smsNumber = "";
			Bundle bundle = intent.getExtras();
			smsNumber = bundle.getString("SmsNumber");
			switch (resultCode) {
			case Activity.RESULT_OK:
				Toast.makeText(getApplicationContext(), smsNumber+"发送成功！",
						Toast.LENGTH_SHORT).show();
				//get appWidgetMannager/remoteViews/componentName for update AppWidget
				appWidgetMannager = AppWidgetManager
						.getInstance(SmsService.this);
				remoteViews = new RemoteViews(SmsService.this.getPackageName(),
						R.layout.widget);
				componentName = new ComponentName(SmsService.this,
						AppWidget.class);
				remoteViews.setTextViewText(R.id.tvWidget,smsNumber+"发送成功！");
				appWidgetMannager.updateAppWidget(componentName, remoteViews);

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
