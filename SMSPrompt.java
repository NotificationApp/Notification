package com.notify.notify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SMSPrompt extends AppCompatActivity {

    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsprompt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textMessage = (TextView) findViewById(R.id.smsMessage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        Log.i("Notify", "In resume");

        Bundle bundle = intent.getBundleExtra("mySMS");

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[0]);

            String message = smsMessage.getMessageBody();
            while (message.contains("Flag")) {
                message = message.replace("FLAG", "");
            }

            textMessage.setText(message);
        } else {
            Log.i("Notify", "In the else block");
            String msg = "No message found";
            textMessage.setText(msg);
            Log.i("Notify", "Executing till here");
        }
    }
}
