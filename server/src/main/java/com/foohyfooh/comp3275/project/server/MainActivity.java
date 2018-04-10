package com.foohyfooh.comp3275.project.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gotoSendAlert = findViewById(R.id.main_gotoSendAlert);
        ListView phoneNumbers = findViewById(R.id.main_phoneNumbers);

        gotoSendAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendAlertActivity.class);
                startActivity(intent);
            }
        });
    }
}
