package com.sosa.practicon2sosagaston;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED )){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},1000);
        }
        empezar();
    }


    @Override
    protected void onStop() {
        super.onStop();
        detener();
    }

    public void empezar(){
        intent= new Intent(this,MensajeTexto.class);
        startService(intent);
    }
    public void detener(){
        if(intent != null) {
            stopService(intent);
        }
    }
}