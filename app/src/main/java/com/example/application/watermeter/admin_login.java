package com.example.application.watermeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class admin_login extends AppCompatActivity {

    Button admin_login_submit;
    EditText admin_login_username;
    EditText admin_login_password;


    FirebaseDatabase fbDatabase;
    DatabaseReference fbDatabaseReference;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_login_submit = findViewById(R.id.user_login_submit);
        admin_login_username = findViewById(R.id.admin_login_username);
        admin_login_password = findViewById(R.id.admin_login_password);

        fbDatabase = FirebaseDatabase.getInstance();
        fbDatabaseReference = fbDatabase.getReference();

        admin_login_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = admin_login_username.getText().toString();
                String password = admin_login_password.getText().toString();

                if(username!=null && password!=null){
                    fetchData(username,password);
                }else if(username==null){
                    Toast.makeText(getApplicationContext(),"Enter your username",Toast.LENGTH_LONG).show();
                }else if(password==null){
                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void choosen_admin_signup(View view){
        startActivity(new Intent(getApplicationContext(), admin_signup.class));
    }

    public void fetchData(String username,String password ){

        String y = username + "_" + password;

        Query query = fbDatabaseReference
                .child("Admin").child(username)
                .orderByChild("username_password")
                .equalTo(y);

        query.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()!=null) {

                    Log.d("dataSnapshot ", dataSnapshot.toString());

                    HashMap<String, Object> studentdata = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.d("dataSnapshot ", studentdata.toString());


                    for (String key : studentdata.keySet()) {

                        Object mObject = studentdata.get(key);
                        HashMap<String, Object> map = (HashMap<String, Object>) mObject;

                        Intent i = new Intent(getApplicationContext(), admin_logged_in.class);
                        i.putExtra("username", map.get("Username").toString());
                        i.putExtra("password", map.get("Password").toString());
                        i.putExtra("society",map.get("Society").toString());
                        i.putExtra("username_password",map.get("username_password").toString());
                        i.putExtra("cost",map.get("Cost").toString());
                        i.putExtra("discount",map.get("Discount").toString());
                        i.putExtra("method",map.get("Method").toString());

                        startActivity(i);
                        finish();
                        break;

                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Not valid", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d( "error ",databaseError.toString());
            }
        });
    }
}
