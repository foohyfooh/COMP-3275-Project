package com.foohyfooh.comp3275.project.server;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SMS = 1;
    private static final String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gotoSendAlert = findViewById(R.id.main_gotoSendAlert);
        ListView phoneNumbers = findViewById(R.id.main_phoneNumbers);
        final PhoneNumberManager phoneNumberManager = new PhoneNumberManager(this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneNumberManager.getPhoneNumbers());
        phoneNumbers.setAdapter(adapter);

        gotoSendAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendAlertActivity.class);
                startActivity(intent);
            }
        });

        phoneNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = adapter.getItem(position);
                adapter.remove(phoneNumber);
                adapter.notifyDataSetChanged();
                phoneNumberManager.deletePhoneNumber(phoneNumber);
                Toast.makeText(MainActivity.this, "Phone number removed", Toast.LENGTH_SHORT).show();
            }
        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, permissions, RC_SMS);
        }
    }
}
