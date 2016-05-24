package com.software.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase myFirebaseRef = new Firebase("https://roche-d-110.firebaseio.com/second");
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void submit(View button) {

        /*EditText etName = (EditText) findViewById(R.id.EditTextName);
        EditText etId = (EditText) findViewById(R.id.EditTextId);
        String name = etName.getText().toString();
        String id = etId.getText().toString();*/

        EditText etMsg = (EditText) findViewById(R.id.EditTextMsg);
        String msg = etMsg.getText().toString();

        Firebase myFirebaseRef = new Firebase("https://roche-d-110.firebaseio.com/first");
        myFirebaseRef.setValue(msg);

        /*Student student = new Student();
        student.setName(name);
        student.setStudentId(id);

        Firebase myFirebaseRef = new Firebase("https://roche-d-110.firebaseio.com/student");
        student.setStudentId(id); myFirebaseRef.child(student.getStudentId()).setValue(student);*/

        //Intent output = new Intent();
        //setResult(RESULT_OK, output);
        //finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
