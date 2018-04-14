package com.foohyfooh.comp3275.project.server;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gotoSendAlert = findViewById(R.id.main_gotoSendAlert);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent3 = getIntent();
                overridePendingTransition(0, 0);
                intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent3);
            }
        });
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
    }



}
