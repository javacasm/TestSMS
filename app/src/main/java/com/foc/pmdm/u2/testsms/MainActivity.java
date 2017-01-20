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
        //Iniciamos Intent.
        Intent sendIntent=new Intent(Intent.ACTION_VIEW);
        //Metemos en la "mochila" del Intent el texto("sms_body") y numero ("address").
        sendIntent.putExtra("sms_body",sTexto);
        sendIntent.putExtra("address",sNumber);
        //Indicamos el tipo de servicio a utilizar.
        sendIntent.setType("vnd.android-dir/mms-sms");
        //Iniciamos activity (lanzar SMS) mandandole el Intent.
        startActivity(sendIntent);

    }

    public void sendSMSbyCode(String sNumber,String sTexto)
    {
        //Iniciamos SmsManager.
        SmsManager smsManager=SmsManager.getDefault();
        //Dividimos el mensaje dado por parametro en varios mensajes organizandolos en un ArrayList.
        ArrayList<String> list=smsManager.divideMessage(sTexto);
        //Por cada posicion del ArrayList se envia un SMS al numero de telefono dado por parametro.
        for(String sFragmento :list) {
            smsManager.sendTextMessage(sNumber,null,sFragmento,null,null);
        }

    }
}
