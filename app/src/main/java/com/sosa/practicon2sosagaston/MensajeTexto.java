package com.sosa.practicon2sosagaston;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MensajeTexto extends Service {

    public MensajeTexto(){
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    int contador = 0;
                    Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
                            String msg = "";

                            msg += " " + cursor.getColumnName(0) + ":" + cursor.getString(0);
                            msg += " " + cursor.getColumnName(2) + ":" + cursor.getString(2);
                            msg += " " + cursor.getColumnName(4) + ":" + cursor.getString(4);
                            msg += " " + cursor.getColumnName(12) + ":" + cursor.getString(12);


                            contador++;
                            Log.d("mensaje", msg);
                            if (contador > 4) {
                                break;
                            }
                        } while (cursor.moveToNext());
                    } else {
                        Log.d("mensaje", "No hay mensajes");
                    }
                    try {
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cursor.close();
                }

            }
        });
        th.start();

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




}
