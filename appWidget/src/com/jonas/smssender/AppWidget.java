package com.jonas.smssender;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AppWidget extends AppWidgetProvider {
    private String result;
    private Intent startPetServiceIntent;
	

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, AppWidget.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            views.setTextViewText(R.id.tvWidget, result);
            // Tell the AppWidgetManager to perform an update on the current App Widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		startPetServiceIntent = new Intent("com.jonas.service.SMS_SERVICE");
		context.startService(startPetServiceIntent);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		context.stopService(new Intent("com.jonas.action.SMS_SERVICE"));
		
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		int resultCode = getResultCode();

		switch (resultCode) {
		case Activity.RESULT_OK:
			result = "Message Sent";
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
