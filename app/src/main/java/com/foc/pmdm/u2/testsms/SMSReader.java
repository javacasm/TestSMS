package com.foc.pmdm.u2.testsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by javacasm on 19/01/2017.
 */

public class SMSReader extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Object []pdusObje=(Object[]) bundle.get("pdus");
        for(int i=0;i<pdusObje.length;i++)
        {
            SmsMessage mensaje=SmsMessage.createFromPdu((byte[])pdusObje[i]);
            String numero=mensaje.getDisplayOriginatingAddress();
            String texto=mensaje.getDisplayMessageBody();

            Toast.makeText(context,"Mensaje de "+numero+": "+texto,Toast.LENGTH_LONG).show();

            //Intent ...
            //startActivity(intent)
        }
    }
}
