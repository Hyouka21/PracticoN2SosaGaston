package com.sosa.practicon2sosagaston;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MensajeTexto extends Service {

    public MensajeTexto(){


    }
    @Override
    public void onCreate() {
        super.onCreate();
        int contador=0;
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String msg = "";

                msg += " " + cursor.getColumnName(0) + ":" + cursor.getString(0);
                msg += " " + cursor.getColumnName(2) + ":" + cursor.getString(2);
                msg += " " + cursor.getColumnName(4) + ":" + cursor.getString(4);
                msg += " " + cursor.getColumnName(12) + ":" + cursor.getString(12);


                contador++;
                Log.d("mensaje",msg);
                if(contador>4){break;}
            } while (cursor.moveToNext());
        } else {
            Log.d("mensaje","No hay mensajes");
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
