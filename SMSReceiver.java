package com.notify.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Himanjan on 03-03-2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    public static String LOG_TAG = SMSReceiver.class.getSimpleName();
    final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        context = MyApplication.getAppContext();

        try {
            if(bundle != null) {
                final Object[] pdusObject = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObject.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObject[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i(LOG_TAG, "Sender number: " + phoneNumber + ". Message is: " + message);

                    /*Intent myIntent = new Intent(context,SMSPrompt.class);
                    myIntent.putExtra("mySMS", bundle);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myIntent);*/

                    Intent myIntent = new Intent(context, PushToServer.class);
                    myIntent.putExtra("mySMS", bundle);
                    context.startService(myIntent);
                }
            } else {
                Log.i(LOG_TAG, "Bundle is null");
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in reading the message");
        }
    }
}
