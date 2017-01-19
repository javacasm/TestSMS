package com.foc.pmdm.u2.testsms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendSMSbyIntent(String sNumber,String sTexto)
    {
        Intent sendIntent=new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body",sTexto);
        sendIntent.putExtra("address",sNumber);
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);

    }

    public void sendSMSbyCode(String sNumber,String sTexto)
    {
        SmsManager smsManager=SmsManager.getDefault();
        ArrayList<String> list=smsManager.divideMessage(sTexto);
        for(String sFragmento :list) {
            smsManager.sendTextMessage(sNumber,null,sFragmento,null,null);
        }

    }
}
