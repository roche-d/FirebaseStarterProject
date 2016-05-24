package com.software.helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class FirebaseService extends Service {
    public FirebaseService() {
    }

    final class MyThread implements Runnable {

        int startId;

        public MyThread(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {

            synchronized (this) {
                try
                {
                    Firebase myFirebaseRef = new Firebase("https://roche-d-110.firebaseio.com/second");
                    myFirebaseRef.setValue("hello");
                    wait(3000);
                    myFirebaseRef.setValue("from");
                    wait(3000);
                    myFirebaseRef.setValue("user");
                    wait(3000);
                    myFirebaseRef.setValue("1");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(startId);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(FirebaseService.this, "Service started", Toast.LENGTH_SHORT).show();

        Firebase myFirebaseRef = new Firebase("https://roche-d-110.firebaseio.com/first");
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Toast.makeText(FirebaseService.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Thread thread = new Thread(new MyThread(startId));
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(FirebaseService.this, "Service stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
