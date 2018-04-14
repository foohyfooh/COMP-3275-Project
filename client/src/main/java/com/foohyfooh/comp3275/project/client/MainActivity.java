package com.foohyfooh.comp3275.project.client;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SMS = 1;
    private static final String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText servicePhoneNumber = findViewById(R.id.main_serviceNumber);
        Button subscribe = findViewById(R.id.main_subscribeToService);
        Button unsubscribe = findViewById(R.id.main_unsubscribeToService);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.main_subscribeToService)
                    sendSMS(servicePhoneNumber.getText().toString(), "subscribe");
                else
                    sendSMS(servicePhoneNumber.getText().toString(), "unsubscribe");
            }
        };

        subscribe.setOnClickListener(listener);
        unsubscribe.setOnClickListener(listener);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, permissions, RC_SMS);
        }
    }

    private void sendSMS(String phoneNumber, String message){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
