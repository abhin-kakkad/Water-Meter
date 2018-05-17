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

public class user_change_password extends AppCompatActivity {

    private EditText user_old_password;
    private EditText user_new_password1;
    private EditText user_new_password2;

    FirebaseDatabase fbDatabase;
    DatabaseReference fbDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change_password);

        user_old_password = (EditText)findViewById(R.id.user_old_password);

        user_new_password1 = (EditText) findViewById(R.id.user_new_password);

        user_new_password2 = (EditText) findViewById(R.id.user_new_password2);

        fbDatabase = FirebaseDatabase.getInstance();
        fbDatabaseReference = fbDatabase.getReference();


        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
    }

    public void user_change(View view){
        //startActivity(new Intent(this, user_login.class));

        Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        final String admin = intent.getStringExtra("admin");

        String old_password = user_old_password.getText().toString().trim();

        if(!old_password.equals(password)){
            Toast.makeText(getApplicationContext(),"Old password is incorrect",Toast.LENGTH_LONG).show();
            return;
        }

        final String new_password  = user_new_password1.getText().toString().trim();

        final String new_password1  = user_new_password2.getText().toString().trim();

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
                        String password = map.get("Password").toString();
                        String username_password = map.get("username_password").toString();

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

                        //Toast.makeText(getApplicationContext(), reading0, Toast.LENGTH_LONG).show();

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


                        HashMap<String, Object> userData = new HashMap<String, Object>();

                        String newpassword = new_password1;
                        String username_newpassword = username + "_" + new_password;

                        userData.put("Username", username);
                        userData.put("Flat", flat);
                        userData.put("Reading0", reading0);
                        userData.put("Reading1", reading1);
                        userData.put("Reading2", reading2);
                        userData.put("Reading3", reading3);
                        userData.put("Reading4", reading4);
                        userData.put("Reading5", reading5);
                        userData.put("Reading6", reading6);
                        userData.put("Reading7", reading7);
                        userData.put("Reading8", reading8);
                        userData.put("Reading9", reading9);
                        userData.put("Reading10", reading10);
                        userData.put("Reading11", reading11);
                        userData.put("Reading12", reading12);
                        userData.put("Date0",date0);
                        userData.put("Date1",date1);
                        userData.put("Date2",date2);
                        userData.put("Date3",date3);
                        userData.put("Date4",date4);
                        userData.put("Date5",date5);
                        userData.put("Date6",date6);
                        userData.put("Date7",date7);
                        userData.put("Date8",date8);
                        userData.put("Date9",date9);
                        userData.put("Date10",date10);
                        userData.put("Date11",date11);
                        userData.put("Date12",date12);
                        userData.put("username_password",username_newpassword);
                        userData.put("Password",newpassword);

                        userData.put("Society",society);
                        userData.put("Cost",cost);
                        userData.put("Final Amount",final_amount);

                        fbDatabaseReference.child("Admin").child(admin).push().updateChildren(userData);


                        for(DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                            areaSnapshot.getRef().setValue(null);
                        }

                        Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Sorry cant find the user in the database", Toast.LENGTH_LONG).show();

                }


            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

    }
}
