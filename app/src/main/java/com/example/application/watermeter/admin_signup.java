package com.example.application.watermeter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private ArrayAdapter<CharSequence> methods;
//    private EditText cost;
//    private EditText discount;
    private Spinner method;

    private TextView usernames;

    private DatabaseReference mDatabase;
    private DatabaseReference myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        admin_signup_submit  = (Button)findViewById(R.id.admin_signup_submit);
        admin_signup_password  = (EditText) findViewById(R.id.admin_signup_password);
        admin_signup_society = (EditText)findViewById(R.id.admin_signup_society);
        admin_signup_password_2 = (EditText)findViewById(R.id.admin_signup_password_2);
//        cost = (EditText)findViewById(R.id.cost);
//        discount = (EditText)findViewById(R.id.discount);

        usernames = (TextView)findViewById(R.id.username);

        method = (Spinner)findViewById(R.id.method);
        methods = ArrayAdapter.createFromResource(this,R.array.Methods,android.R.layout.simple_spinner_item);
        methods.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        method.setAdapter(methods);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        myDatabase = FirebaseDatabase.getInstance().getReference();

        Query q = myDatabase
                .child("Admin")
                .orderByChild("username");

        final String[] username = new String[1];
        final long[] size = new long[1];

        q.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                size[0] = dataSnapshot.getChildrenCount();
                size[0]++;
                String t = "";
                t = "Your Username : " + t + String.valueOf(size[0]);
                usernames.setText(t);
                //Toast.makeText(getApplicationContext(), t, Toast.LENGTH_LONG).show();
                username[0] = String.valueOf(size[0]);
                //usernames.setText(username[0]);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        admin_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String password = admin_signup_password.getText().toString().trim();
                final String society = admin_signup_society.getText().toString().trim();
//                final String costs = cost.getText().toString().trim();
//                final String discounts = discount.getText().toString().trim();

                String password_2 = admin_signup_password_2.getText().toString().trim();

//                if(TextUtils.isEmpty(username[0])) {
//                    Toast.makeText(admin_signup.this,"Please enter your username",Toast.LENGTH_SHORT).show();
//                    return;
//                }

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

//                if(TextUtils.isEmpty(costs)){
//                    Toast.makeText(admin_signup.this,"Please enter the cost price",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                final String method1 = method.getSelectedItem().toString();

//                if(method1.equals("Method 2")){
//                    if(TextUtils.isEmpty(discounts)){
//                        Toast.makeText(admin_signup.this,"Please enter the units till which service is free",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }

                Query query = mDatabase
                        .child("Admin")
                        .orderByChild("username")
                        .equalTo(username[0]);

                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.getValue() != null) {
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                            return;
                        } else {

//                            long size = dataSnapshot.getChildrenCount();
//                            String t = "";
//                            t = t + String.valueOf(size);
//                            Toast.makeText(getApplicationContext(), t, Toast.LENGTH_LONG).show();

                            HashMap<String, String> userData = new HashMap<String, String>();

                            String y = username[0] + "_" + password;

                            userData.put("Username", username[0]);
                            userData.put("Password", password);
                            userData.put("Society", society);
                            userData.put("username_password", y);
                            if(method1.equals("Method 1")){
                                userData.put("Method",String.valueOf(1));
                                userData.put("Cost","0");
                                userData.put("Discount","0");
                            }else {
                                userData.put("Method", String.valueOf(2));
                                userData.put("Cost","0");
                                userData.put("Discount","0");
                            }

                            Log.d("hello", "how");

                            mDatabase.child("Admin").child(username[0]).push().setValue(userData);

                            //              mDatabase.child("Hello").setValue(userData);

                            Intent i = new Intent(getApplicationContext(), EnterSocietyDetails.class);
                            i.putExtra("username", username[0]);
                            i.putExtra("password", password);
                            i.putExtra("society",society);
                            i.putExtra("username_password",y);
                            i.putExtra("cost","0");
                            i.putExtra("discount","0");
                            i.putExtra("method",method1);
                            startActivity(i);
                            finish();


//                            startActivity(new Intent(getApplicationContext(), admin_login.class));
//                            finish();
//
//                            Log.d("hello1", "how1");


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



