package com.foc.pmdm.u2.testsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



   // Listado de permisos https://developer.android.com/reference/android/Manifest.permission.html

    // Permisos agrupados https://developer.android.com/guide/topics/security/permissions.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Método que necesita permiso para recibir MMS
    public void ReceiveMMS()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_MMS) == PackageManager.PERMISSION_GRANTED) {
                // Hago el proceso de recepción
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_MMS},
                    ID_PETICION_PERMISOS_RECEIVE_MMS);
        }
    }

    public void sendSMSbyIntent(String sNumber,String sTexto)
    {   //No necesitamos permisos
        Intent sendIntent=new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body",sTexto);
        sendIntent.putExtra("address",sNumber);
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);

    }
    // Usamos estas constantes para saber a qué petición de permisos nos estamos refiriendo
    static final int ID_PETICION_PERMISOS_SEND_SMS=76567; // Es la forma de crear una constante en Java, al ser final no cambia y static para evitar que se tenga que crear en cada instanacia
    static final int ID_PETICION_PERMISOS_RECEIVE_MMS=76577;
    public void sendSMSbyCode()
    { // Necesito los permisos

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            String sTexto="Texto"; // Lo obtendríamos de un EditText o similar
            String sNumber="+00000000000"; // Lo obtendríamos de un EditText o similar
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> list = smsManager.divideMessage(sTexto);
            for (String sFragmento : list) {
                smsManager.sendTextMessage(sNumber, null, sFragmento, null, null);
            }

        }
        else {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    ID_PETICION_PERMISOS_SEND_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch(requestCode)
        {
            case ID_PETICION_PERMISOS_SEND_SMS:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   // Ahora hacemos el envio de SSM
                    sendSMSbyCode();
                }
                else
                {
                    // No han dado permiso avisamos de que no se puede hacer nada
                    Toast.makeText(this,"No se tiene permiso",Toast.LENGTH_LONG).show();
                }
                break;
            case ID_PETICION_PERMISOS_RECEIVE_MMS:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    ReceiveMMS();
                }
                else
                {
                    // No han dado permiso avisamos de que no se puede hacer nada
                    Toast.makeText(this,"No se tiene permiso",Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

}
