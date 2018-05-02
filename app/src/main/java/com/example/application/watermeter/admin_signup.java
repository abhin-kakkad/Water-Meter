package com.example.application.watermeter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class admin_signup extends AppCompatActivity {


    private Button admin_signup_submit;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private EditText admin_signup_password;
    private EditText admin_signup_society;
    private EditText admin_signup_password_2;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        admin_signup_submit  = (Button)findViewById(R.id.admin_signup_submit);
        admin_signup_password  = (EditText) findViewById(R.id.admin_signup_password);
        admin_signup_society = (EditText)findViewById(R.id.admin_signup_society);
        admin_signup_password_2 = (EditText)findViewById(R.id.admin_signup_password_2);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        admin_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String password = admin_signup_password.getText().toString().trim();
                final String society = admin_signup_society.getText().toString().trim();
                final String username = society + "_admin";
                String password_2 = admin_signup_password_2.getText().toString().trim();

                if(TextUtils.isEmpty(username)) {
                    Toast.makeText(admin_signup.this,"Please enter your username",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(admin_signup.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password_2)){
                    Toast.makeText(admin_signup.this,"Please re enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(password_2)){
                    Toast.makeText(admin_signup.this,"Re entered password doesnt match with the one provided",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(society)){
                    Toast.makeText(admin_signup.this,"Please enter your society",Toast.LENGTH_SHORT).show();
                    return;
                }

                Query query = mDatabase
                        .child("Admin")
                        .orderByChild("username")
                        .equalTo(username);

                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue()!=null) {
                            Toast.makeText(getApplicationContext(),"Username already taken",Toast.LENGTH_LONG).show();
                            return;
                        }else{

                            HashMap<String, String> userData = new HashMap<String, String>();

                            String y = username + "_" + password;

                            userData.put("Username", username);
                            userData.put("Password", password);
                            userData.put("Society", society);
                            userData.put("username_password",y);

                            Log.d("hello","how");

                            mDatabase.child("Admin").push().setValue(userData);

            //              mDatabase.child("Hello").setValue(userData);


                            startActivity(new Intent(getApplicationContext(), admin_login.class));
                            finish();

                            Log.d("hello1","how1");


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }
        });
    }



}



