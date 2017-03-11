package com.notify.notify;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Himanjan on 11-03-2017.
 */

public class PushToServer extends IntentService {
    private static String TAG = PushToServer.class.getSimpleName();

    public PushToServer() {
        super(PushToServer.class.getSimpleName());
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Notify", "In resume");

        Bundle bundle = intent.getBundleExtra("mySMS");

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[0]);

            String message = smsMessage.getMessageBody();
            pushData(message);
        } else {
            Log.i("Notify", "In the else block");
            Log.i("Notify", "Executing till here");
        }
    }

    /**
     * Posting the message received to server
     *
     * @param message received in the SMS
     */
    private void pushData(final String message) {
        Log.i(TAG, "Mobile Number: " + message);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_PUSH_MESSAGE_DETAILS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.i(TAG, response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("message", message);

                Log.i(TAG, "Posting params i: " + params.toString());
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }
}
