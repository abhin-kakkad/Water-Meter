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

public class user_login extends AppCompatActivity {

    private EditText user_login_username;
    private EditText user_login_password;
    private Button user_login_submit;
    private EditText user_login_admin;

    FirebaseDatabase fbDatabase;
    DatabaseReference fbDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        user_login_password = (EditText)findViewById(R.id.user_login_password);
        user_login_submit = (Button)findViewById(R.id.user_login_submit);
        user_login_username = (EditText)findViewById(R.id.user_login_username);
        user_login_admin = (EditText)findViewById(R.id.user_login_admin);

        fbDatabase = FirebaseDatabase.getInstance();
        fbDatabaseReference = fbDatabase.getReference();

        user_login_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = user_login_username.getText().toString().trim();
                String password = user_login_password.getText().toString().trim();
                String admin = user_login_admin.getText().toString().trim();

                if(username!=null && password!=null && admin!=null){
                    fetchData(username,password,admin);
                }else if(username==null){
                    Toast.makeText(getApplicationContext(),"Enter your username",Toast.LENGTH_LONG).show();
                }else if(password==null){
                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_LONG).show();
                }else if(admin==null){
                    Toast.makeText(getApplicationContext(),"Enter name of your admin",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void fetchData(final String username, final String password, final String admin ){

        final String y = username + "_" + password;

        Query query = fbDatabaseReference
                .child("Admin").child(admin)
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


                        String flat = map.get("Flat").toString();
                        String cost = map.get("Cost").toString();
                        String final_amount = map.get("Final Amount").toString();
                        String society = map.get("Society").toString();

                        String reading0 = map.get("Reading0").toString();
                        String reading1 = map.get("Reading1").toString();
                        String reading2 = map.get("Reading2").toString();
                        String reading3 = map.get("Reading3").toString();
                        String reading4 = map.get("Reading4").toString();
                        String reading5 = map.get("Reading5").toString();
                        String reading6 = map.get("Reading6").toString();
                        String reading7 = map.get("Reading7").toString();
                        String reading8 = map.get("Reading8").toString();
                        String reading9 = map.get("Reading9").toString();
                        String reading10 = map.get("Reading10").toString();
                        String reading11 = map.get("Reading11").toString();
                        String reading12 = map.get("Reading12").toString();

                        String date0 = map.get("Date0").toString();
                        String date1 = map.get("Date1").toString();
                        String date2 = map.get("Date2").toString();
                        String date3 = map.get("Date3").toString();
                        String date4 = map.get("Date4").toString();
                        String date5 = map.get("Date5").toString();
                        String date6 = map.get("Date6").toString();
                        String date7 = map.get("Date7").toString();
                        String date8 = map.get("Date8").toString();
                        String date9 = map.get("Date9").toString();
                        String date10 = map.get("Date10").toString();
                        String date11 = map.get("Date11").toString();
                        String date12 = map.get("Date12").toString();

                        Intent i = new Intent(getApplicationContext(), user_logged_in.class);
                        i.putExtra("username", username);
                        i.putExtra("password", password);
                        i.putExtra("society",society);
                        i.putExtra("username_password",y);
                        i.putExtra("flat", flat);
                        i.putExtra("cost", cost);
                        i.putExtra("final_amount",final_amount);
                        i.putExtra("reading0",reading0);
                        i.putExtra("reading1",reading1);
                        i.putExtra("reading2",reading2);
                        i.putExtra("reading3",reading3);
                        i.putExtra("reading4",reading4);
                        i.putExtra("reading5",reading5);
                        i.putExtra("reading6",reading6);
                        i.putExtra("reading7",reading7);
                        i.putExtra("reading8",reading8);
                        i.putExtra("reading9",reading9);
                        i.putExtra("reading10",reading10);
                        i.putExtra("reading11",reading11);
                        i.putExtra("reading12",reading12);
                        i.putExtra("date0",date0);
                        i.putExtra("date1",date1);
                        i.putExtra("date2",date2);
                        i.putExtra("date3",date3);
                        i.putExtra("date4",date4);
                        i.putExtra("date5",date5);
                        i.putExtra("date6",date6);
                        i.putExtra("date7",date7);
                        i.putExtra("date8",date8);
                        i.putExtra("date9",date9);
                        i.putExtra("date10",date10);
                        i.putExtra("date11",date11);
                        i.putExtra("date12",date12);
                        i.putExtra("admin",admin);

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
