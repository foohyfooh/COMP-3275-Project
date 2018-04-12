package com.foohyfooh.comp3275.project.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            PhoneNumberManager manager = new PhoneNumberManager(context);
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] recMsg = new SmsMessage[pdus.length];
            for (int i = 0; i < recMsg.length; i++){
                String format = bundle.getString("format");
                recMsg[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);

                String number = recMsg[i].getOriginatingAddress();
                String message = recMsg[i].getMessageBody();
                if(message.equals("subscribe")) manager.insertPhoneNumber(number);
                else if(message.equals("unsubscribe")) manager.deletePhoneNumber(number);
            }
        }
    }
}
