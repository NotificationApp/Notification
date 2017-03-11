package com.notify.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Himanjan on 05-03-2017.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        MyPhoneStateListener myPhoneStateListener = new MyPhoneStateListener();
        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                try {
                    Thread.sleep(35000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CollectCallLogs collectCallLogs = new CollectCallLogs();
                collectCallLogs.getCallDetails(mContext);
            }
        }
    }
}