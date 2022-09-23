package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etMsj, etCel;
    Button btnEnviar, btnAddPhone;
    List<String> numeros = new ArrayList<>();
    TextView mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMsj = findViewById(R.id.etMsj);
        etCel = findViewById(R.id.etCel);
        btnEnviar = findViewById(R.id.btnSend);
        btnAddPhone = findViewById(R.id.btnAddCel);
        mostrar = findViewById(R.id.tvwMostrar);

        // Pedir permisos
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},1);
        }


        btnEnviar.setOnClickListener(v -> {
           SmsManager smsManager = SmsManager.getDefault();
            String numCel = etCel.getText().toString();
            String msg = etMsj.getText().toString();

            for (String num : numeros) {
                smsManager.sendTextMessage(num, null, msg, null, null);
            }

            Toast.makeText(MainActivity.this,"Mensaje Enviado", Toast.LENGTH_LONG).show();

       });

        btnAddPhone.setOnClickListener(v-> {
            String numCel = etCel.getText().toString();
            Toast.makeText(MainActivity.this,numCel + " Added", Toast.LENGTH_LONG).show();
            numeros.add(numCel);

            String NumerosTxt = "";
            for (String num : numeros) {
                NumerosTxt += "\n" + num.toString();
            }

            etCel.setText("");
            mostrar.setText("");

            mostrar.setText(NumerosTxt);
        });

    }
}