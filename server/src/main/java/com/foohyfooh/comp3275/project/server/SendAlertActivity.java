package com.foohyfooh.comp3275.project.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SendAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);
        final EditText message = findViewById(R.id.sendAlert_message);
        Button send = findViewById(R.id.sendAlert_send);
        final PhoneNumberManager phoneNumberManager = new PhoneNumberManager(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = message.getText().toString();
                if(text.isEmpty()) {
                    Toast.makeText(SendAlertActivity.this, "Enter alert message", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> phoneNumbers = phoneNumberManager.getPhoneNumbers();

                if(phoneNumbers.isEmpty()){
                    Toast.makeText(SendAlertActivity.this, "No phone numbers exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                for(String phoneNumber: phoneNumbers){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, text, null, null);
                }
            }
        });
    }
}
